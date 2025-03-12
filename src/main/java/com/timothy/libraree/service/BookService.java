package com.timothy.libraree.service;


import com.timothy.libraree.model.BookRequest;
import com.timothy.libraree.model.BookListResponse;
import com.timothy.libraree.model.BookResponse;

public interface BookService {
    BookListResponse findAllBooks();
    BookResponse createBook(BookRequest req);
}
