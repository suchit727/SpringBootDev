package com.example.springwithresttemplate.exception;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {
    @InjectMocks
    GlobalExceptionHandler sut;
    @Mock
    WebRequest webRequest;
    static final String GLOBAL_EXCEPTION_ERROR_MESSAGE = "Global Exception Error Message";

    static final String requestPath="/some/path";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new GlobalExceptionHandler();
        when(webRequest.getContextPath()).thenReturn(requestPath);
    }
    @Test
    void handleIllegalStateException(){
        IllegalStateException illegalStateException=new IllegalStateException(GLOBAL_EXCEPTION_ERROR_MESSAGE);
        ResponseEntity<ErrorDetails> responseEntity = sut.illegalStateException(illegalStateException,webRequest);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getStatusCode());

    }
    @Test
    void handleConectException(){
        ConnectException connectException=new ConnectException(GLOBAL_EXCEPTION_ERROR_MESSAGE);
        ResponseEntity<ErrorDetails> responseEntity=sut.globalException(connectException,webRequest);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getStatusCode());

    }
    @Test
    void handleNotFoundException(){
         NotFoundException notFoundException=new NotFoundException(GLOBAL_EXCEPTION_ERROR_MESSAGE);
         ResponseEntity<ErrorDetails> responseEntity=sut.notFoundException(notFoundException,webRequest);
         assertNotNull(responseEntity);
         assertNotNull(responseEntity.getStatusCode());
    }

}
