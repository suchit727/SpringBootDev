package com.crossasyst.callingservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class BadRequestException extends HttpClientErrorException {

    public BadRequestException(HttpStatusCode statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
