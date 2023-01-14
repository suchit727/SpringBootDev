package com.crossasyst.callingservice.exceptionhandling;

import com.crossasyst.callingservice.exceptions.BadRequestException;
import com.crossasyst.callingservice.exceptions.ClientException;
import com.crossasyst.callingservice.exceptions.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "com.crossasyst.callingservice")

public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestResponse(BadRequestException badRequestException, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorResponse(badRequestException.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientException.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> clientExceptionResponse(ClientException badRequestException, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorResponse(badRequestException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), webRequest.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> personNotFoundExceptionResponse(PersonNotFoundException personNotFoundException, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorResponse(personNotFoundException.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now(), webRequest.getDescription(false)), HttpStatus.NOT_FOUND);
    }

}
