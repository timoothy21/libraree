package com.timothy.libraree.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class APIExceptionHandler {

    @ExceptionHandler(APIException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody APIExceptionResponse handleAPIException(APIException e) {
        return APIExceptionResponse.builder()
                .code(e.getHttpStatus().value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody APIExceptionResponse handler(MethodArgumentNotValidException e) {
        List<String> errorMessages = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> errorMessages.add(fieldError.getDefaultMessage()));
        return APIExceptionResponse.builder()
                .code(e.getStatusCode().value())
                .message(errorMessages.get(0))
                .timestamp(LocalDateTime.now())
                .build();
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public @ResponseBody APIExceptionResponse handleGeneric(Exception e) {
//        log.error("Error: " + HttpStatus.INTERNAL_SERVER_ERROR);
//        return APIExceptionResponse.builder()
//                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message(e.getMessage())
//                .timestamp(LocalDateTime.now())
//                .build();
//    }
}
