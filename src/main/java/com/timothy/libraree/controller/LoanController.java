package com.timothy.libraree.controller;

import com.timothy.libraree.exception.APIException;
import com.timothy.libraree.model.LoanListResponse;
import com.timothy.libraree.model.LoanRequest;
import com.timothy.libraree.model.LoanResponse;
import com.timothy.libraree.model.ReturnBookResponse;
import com.timothy.libraree.service.LoanService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loan")
@Slf4j
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping()
    public ResponseEntity<LoanResponse> borrowBook(@RequestBody @Valid LoanRequest req) {
        LoanResponse res = loanService.borrowBook(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/status")
    public ResponseEntity<LoanListResponse> statusLoan() {
        LoanListResponse res = loanService.statusLoan();
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{loanId}/return")
    public ResponseEntity<ReturnBookResponse> returnBook(@PathVariable("loanId") Integer loanId) {
        ReturnBookResponse res = loanService.returnBook(loanId);
        return ResponseEntity.ok(res);
    }


}
