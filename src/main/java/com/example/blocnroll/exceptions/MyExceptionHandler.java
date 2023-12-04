package com.example.blocnroll.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionsDetails> handlerMethodArgumentNotValid (MethodArgumentNotValidException bre) {
        return new ResponseEntity<>( ExceptionsDetails.builder()
                .codigo(HttpStatus.BAD_REQUEST.value())
                .titulo("Bad request")
                .detail(bre.getFieldError().getDefaultMessage())
                .timeStamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionsDetails> handlerBadRequestException (BadRequestException bre) {
        return new ResponseEntity<>( ExceptionsDetails.builder()
                .codigo(HttpStatus.BAD_REQUEST.value())
                .titulo("Bad request")
                .detail(bre.getMessage())
                .timeStamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<ExceptionsDetails> handlerOwnerNotFoundException (OwnerNotFoundException bre) {
        return new ResponseEntity<>( ExceptionsDetails.builder()
                .codigo(HttpStatus.BAD_REQUEST.value())
                .titulo("Bad request")
                .detail(bre.getMessage())
                .timeStamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

}
