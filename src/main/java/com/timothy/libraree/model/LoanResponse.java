package com.timothy.libraree.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponse {
    private String loanId;
    private String userName;
    private String bookTitle;
    private String returnDate;
    private String borrowedTime;
    private String actualReturnTime;
    private String expectedReturnTime;
    private String status;
}
