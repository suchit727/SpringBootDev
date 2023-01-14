package com.crossasyst.onetoone.exceptionhandling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@NoArgsConstructor
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

public class UnexpectedException extends RuntimeException{
    public UnexpectedException(String message) {
        super(message);
    }
}
