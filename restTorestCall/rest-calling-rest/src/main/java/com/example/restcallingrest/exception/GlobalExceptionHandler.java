package com.example.restcallingrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.ConnectException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorDetails> globalException(ConnectException connectException, WebRequest webRequest) {
        return new ResponseEntity(new ErrorDetails(new Date(), connectException.getMessage(), webRequest.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorDetails>  illegalStateException(IllegalStateException illegalStateException,WebRequest webRequest) {
    return new ResponseEntity(new ErrorDetails(new Date(),"This error is interesting" , webRequest.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> notFoundException(NotFoundException notFoundException,WebRequest webRequest){
        return new ResponseEntity(new ErrorDetails(new Date(),"Not Found" , webRequest.getDescription(false)),HttpStatus.NOT_FOUND);
    }
    /*@ExceptionHandler(PersonAddressClientException.class)
    public ResponseEntity<ErrorDetails> PersonAddressClientExcep(PersonAddressClientException personAddressClientException,WebRequest webRequest){
        return new ResponseEntity(new ErrorDetails(new Date(),"This is internal error" , webRequest.getDescription(false)),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PersonAddressBadRequestException.class)
    public ResponseEntity<ErrorDetails> PersonAddressBadRequestExcep(PersonAddressBadRequestException personAddressClientException,WebRequest webRequest){
        return new ResponseEntity(new ErrorDetails(new Date(),"This is internal error" , webRequest.getDescription(false)),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorDetails> serverException(HttpServerErrorException httpServerErrorException, WebRequest webRequest) {
        return new ResponseEntity( new ErrorDetails(new Date(), httpServerErrorException.getMessage(), webRequest.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
