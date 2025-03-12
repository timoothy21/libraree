package com.timothy.libraree.model.mapper;

import com.timothy.libraree.entity.Book;
import com.timothy.libraree.model.BookRequest;
import com.timothy.libraree.model.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookResponse mapToBookResponse(Book book);

    Book mapToBook(BookRequest bookRequest);
}
