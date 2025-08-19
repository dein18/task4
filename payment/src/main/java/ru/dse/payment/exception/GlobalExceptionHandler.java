package ru.dse.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorResponse> handlePaymentException(PaymentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.BAD_REQUEST.toString())
                        .log(ex.fillInStackTrace().toString())
                        .build());
    }

    @ExceptionHandler(ProductNotFoundExeption.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ProductNotFoundExeption ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.NOT_FOUND.toString())
                        .log(Arrays.toString(ex.getStackTrace()))
                        .build());
    }


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpClientErrorException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.BAD_REQUEST.toString())
                        .log(Arrays.toString(ex.getStackTrace()))
                        .build());
    }
}