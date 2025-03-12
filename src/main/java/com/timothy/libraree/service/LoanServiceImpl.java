package com.timothy.libraree.service;

import com.timothy.libraree.entity.Book;
import com.timothy.libraree.entity.BookLoans;
import com.timothy.libraree.entity.User;
import com.timothy.libraree.exception.APIException;
import com.timothy.libraree.model.*;
import com.timothy.libraree.model.mapper.BookLoanMapper;
import com.timothy.libraree.repository.BookRepository;
import com.timothy.libraree.repository.LoanRepository;
import com.timothy.libraree.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanServiceImpl implements LoanService{

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional
    public LoanResponse borrowBook(LoanRequest req) {
        User user = userRepository
                .findById(req.getNIK())
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "User Not found"));

        Book book = bookRepository
                .findById(req.getIsbn())
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "Book Not found"));

        LocalDate timeToReturn = LocalDate.now().plusDays(30);
        if (req.getReturnDate().isAfter(timeToReturn)) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Book can't be borrow more than 30 days!");
        }

        if(book.getStock() == 0) {
            throw new APIException(HttpStatus.ACCEPTED, "Book is currently out of stock");
        }

        Optional<BookLoans> isAlreadyLoan = loanRepository.findActiveLoadByNIK(req.getNIK());

        if (isAlreadyLoan.isPresent()) {
            throw new APIException(HttpStatus.BAD_REQUEST, "User cannot borrow another book until User return the previous one");
        }
        LocalDateTime returnTime = req.getReturnDate().atTime(23,59,59);

        BookLoans bookLoans = BookLoans.builder()
                .user(user)
                .book(book)
                .borrowedTime(LocalDateTime.now())
                .expectedReturnTime(returnTime)
                .build();

        bookLoans.getBook().setStock(bookLoans.getBook().getStock()-1);

        loanRepository.save(bookLoans);
        return BookLoanMapper.INSTANCE.mapToLoanResponse(bookLoans);
    }

    @Override
    public LoanListResponse statusLoan() {
        List<StatusLoanBook> listLoan = loanRepository.findAllLoanStatusInterface();
        List<LoanResponse> loans =  new ArrayList<>();
        for (StatusLoanBook loan : listLoan) {
            loans.add(LoanResponse.builder()
                    .loanId(loan.getId())
                    .userName(loan.getName())
                    .bookTitle(loan.getTitle())
                    .borrowedTime(loan.getBorrowedTime())
                    .actualReturnTime(loan.getActualReturnTime())
                    .expectedReturnTime(loan.getExpectedReturnTime())
                    .status(loan.getStatus())
                    .build());
        }
        return LoanListResponse.builder().loans(loans).build();
    }

    @Override
    @Transactional
    public ReturnBookResponse returnBook(Integer loanId) {
        BookLoans loan = loanRepository.findFirstByIdAndActualReturnTimeIsNull(loanId);
        if (loan == null) {
            throw new APIException(HttpStatus.NOT_FOUND, "Loan data not found");
        }

        LocalDateTime returnTime = LocalDateTime.now();
        loan.setActualReturnTime(returnTime);
        loan.getBook().setStock(loan.getBook().getStock()+1);
        loanRepository.save(loan);

        String status = returnTime.isAfter(loan.getExpectedReturnTime()) ? "Late Returned" : "On Time";

        return ReturnBookResponse.builder()
                .loanId(loanId.toString())
                .bookTitle(loan.getBook().getTitle())
                .borrowedTime(loan.getBorrowedTime().toString())
                .actualReturnTime(loan.getActualReturnTime().toString())
                .expectedReturnTime(loan.getExpectedReturnTime().toString())
                .status(status)
                .build();
    }
}
