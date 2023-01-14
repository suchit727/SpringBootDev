package com.crossasyst.onetoone.exceptionhandling;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String message){
        super(message);
    }
}
