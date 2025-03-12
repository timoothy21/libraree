package com.timothy.libraree.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class BookRequest {
    @NotBlank(message = "ISBN tidak boleh kosong")
    private String isbn;
    @NotNull(message = "Title tidak boleh kosong")
    private String title;
    @NotNull(message = "Stock tidak boleh kosong")
    @Min(value = 1, message = "Stock tidak boleh kurang atau sama dengan 0")
    private Integer stock;
}
