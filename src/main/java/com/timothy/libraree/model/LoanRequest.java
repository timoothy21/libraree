package com.timothy.libraree.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class LoanRequest {
    @NotNull(message = "NIK tidak boleh kosong")
    @JsonProperty("NIK")
    private String NIK;
    @NotBlank(message = "ISBN tidak boleh kosong")
    private String isbn;
    @Future(message = "Return Date must be in the future")
    private LocalDate returnDate;
}
