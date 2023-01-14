package com.crossasyst.callingservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)

public class PersonNotFoundException extends HttpClientErrorException {
    public PersonNotFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    ;
}
