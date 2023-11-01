package com.example.blocnroll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(String mensagem) {
        super(mensagem);
    }
}
