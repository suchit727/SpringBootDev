package com.crossasyst.callingservice.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;
    private String path;
}