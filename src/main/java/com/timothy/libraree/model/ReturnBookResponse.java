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
public class ReturnBookResponse {
    private String message;
    private String loanId;
    private String bookTitle;
    private String borrowedTime;
    private String actualReturnTime;
    private String expectedReturnTime;
    private String status;
}
