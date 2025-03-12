package com.timothy.libraree.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private String isbn;
    private String title;
    private Integer stock;

}
