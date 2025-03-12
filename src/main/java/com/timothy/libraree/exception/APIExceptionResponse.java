package com.timothy.libraree.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class APIExceptionResponse {
    private int code;
    private String message;
    private LocalDateTime timestamp;
}
