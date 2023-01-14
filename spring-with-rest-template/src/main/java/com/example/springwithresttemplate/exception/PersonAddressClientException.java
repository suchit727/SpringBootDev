package com.example.springwithresttemplate.exception;

public class PersonAddressClientException  extends RuntimeException {
    public PersonAddressClientException(String message) {
        super(message);
    }

    public PersonAddressClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonAddressClientException(Throwable cause) {
        super(cause);
    }
}
