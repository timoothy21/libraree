package com.timothy.libraree.repository;

import com.timothy.libraree.entity.BookLoans;
import com.timothy.libraree.model.LoanListResponse;
import com.timothy.libraree.model.LoanResponse;
import com.timothy.libraree.model.StatusLoanBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<BookLoans, Integer> {

    @Query(value = """
            SELECT bl.* FROM book_loans bl
            JOIN user u ON u.NIK = bl.user
            JOIN book b ON b.isbn = bl.book
            WHERE bl.user = :NIK and bl.actual_return_time IS NULL
            LIMIT 1;
        """, nativeQuery = true)
    Optional<BookLoans> findActiveLoadByNIK(String NIK);

    @Query(value= """
            SELECT id, u.name, b.title, borrowed_time, actual_return_time, expected_return_time, 
            CASE 
                WHEN bl.actual_return_time IS NOT NULL AND bl.actual_return_time > bl.expected_return_time then 'Late Returned' 
                WHEN bl.actual_return_time IS NOT NULL AND bl.actual_return_time < bl.expected_return_time then 'On Time' 
                WHEN bl.actual_return_time IS NULL AND NOW() > bl.expected_return_time then 'Overdue' 
                WHEN bl.actual_return_time IS NULL AND NOW() < bl.expected_return_time then 'On Borrow' 
            END as status 
            FROM book_loans bl 
            JOIN `user` u ON u.NIK = bl.`user` 
            JOIN book b ON b.isbn = bl.book 
            """, nativeQuery = true)
    List<StatusLoanBook> findAllLoanStatusInterface();

    BookLoans findFirstByIdAndActualReturnTimeIsNull(Integer id);

//    BookLoans findFirstByUser(User user);

}
