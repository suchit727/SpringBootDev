package com.example.springwithresttemplate.exception;

import org.springframework.core.ExceptionDepthComparator;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<?> globalException(HandleExce exce, WebRequest webRequest) {
        exce.setMessage("this is 500");
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exce.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<?> globalException(HttpServerErrorException httpServerErrorException, WebRequest webRequest) {
//        exce.setMessage("this is 500");
        ErrorDetails errorDetails = new ErrorDetails(new Date(), httpServerErrorException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorDetails>  IllegalStateException(IllegalStateException illegalStateException,WebRequest webRequest) {
     HandleExce handleExce=new HandleExce("This error is interesting");
     ErrorDetails errorDetails = new ErrorDetails(new Date(),handleExce.getMessage() , webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
