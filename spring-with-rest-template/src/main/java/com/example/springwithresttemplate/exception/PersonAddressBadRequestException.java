package com.example.springwithresttemplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PersonAddressBadRequestException extends RuntimeException{
    public PersonAddressBadRequestException(String message) {
        super(message);
    }

    public PersonAddressBadRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
