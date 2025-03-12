package com.timothy.libraree.service;

import com.timothy.libraree.entity.Book;
import com.timothy.libraree.exception.APIException;
import com.timothy.libraree.model.BookRequest;
import com.timothy.libraree.model.BookListResponse;
import com.timothy.libraree.model.BookResponse;
import com.timothy.libraree.model.mapper.BookMapper;
import com.timothy.libraree.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public BookListResponse findAllBooks() {
        List<BookResponse> data = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        books.forEach(book -> data.add(BookMapper.INSTANCE.mapToBookResponse(book)));
        return BookListResponse.builder().books(data).build();
    }

    @Override
    public BookResponse createBook(BookRequest req) {
        Optional<Book> existBook = bookRepository.findById(req.getIsbn());

        if(existBook.isPresent()) {
            throw new APIException(HttpStatus.CONFLICT, "Book with ISBN " + req.getIsbn() + " already exists!");
        }

        Book book = BookMapper.INSTANCE.mapToBook(req);
        bookRepository.save(book);
        return BookMapper.INSTANCE.mapToBookResponse(book);
    }
}
