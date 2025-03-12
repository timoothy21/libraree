package com.timothy.libraree.controller;

import com.timothy.libraree.model.BookRequest;
import com.timothy.libraree.model.BookListResponse;
import com.timothy.libraree.model.BookResponse;
import com.timothy.libraree.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping()
    public ResponseEntity<BookListResponse> getAllBook(){
        BookListResponse books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping()
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookRequest req) {
        BookResponse res = bookService.createBook(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
