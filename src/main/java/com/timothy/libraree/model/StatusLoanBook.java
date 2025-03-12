package com.timothy.libraree.model;

import com.timothy.libraree.entity.Book;
import com.timothy.libraree.entity.User;

public interface StatusLoanBook {
    String getId();
    String getName();
    String getTitle();
    String getBorrowedTime();
    String getActualReturnTime();
    String getExpectedReturnTime();
    String getStatus();
}
