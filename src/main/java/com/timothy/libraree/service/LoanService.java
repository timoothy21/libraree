package com.timothy.libraree.service;

import com.timothy.libraree.model.LoanListResponse;
import com.timothy.libraree.model.LoanRequest;
import com.timothy.libraree.model.LoanResponse;
import com.timothy.libraree.model.ReturnBookResponse;

public interface LoanService {
    LoanResponse borrowBook(LoanRequest req);

    LoanListResponse statusLoan();

    ReturnBookResponse returnBook(Integer loanId);
}
