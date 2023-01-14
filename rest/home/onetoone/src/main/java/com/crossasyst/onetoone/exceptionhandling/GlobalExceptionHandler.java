/*
package com.crossasyst.onetoone.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {PersonNotFoundException.class})
    */
/* @ResponseStatus(HttpStatus.NOT_FOUND)*//*

    public ResponseEntity<ErrorResponse> errorResponse(PersonNotFoundException personNotFoundException, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorResponse(personNotFoundException.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now(), webRequest.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UnexpectedException.class})
    */
/*@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)*//*

    public ResponseEntity<ErrorResponse> badRequestResponse(UnexpectedException badRequestException, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorResponse(badRequestException.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

  */
/*  @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), webRequest.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }*//*



}
*/
