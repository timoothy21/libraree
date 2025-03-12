package com.timothy.libraree.model.mapper;

import com.timothy.libraree.entity.BookLoans;
import com.timothy.libraree.model.LoanResponse;
import com.timothy.libraree.model.StatusLoanBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookLoanMapper {
    BookLoanMapper INSTANCE = Mappers.getMapper(BookLoanMapper.class);

    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "book.title", target = "bookTitle")
    @Mapping(source = "expectedReturnTime", target = "returnDate")
    LoanResponse mapToLoanResponse(BookLoans req);
}
