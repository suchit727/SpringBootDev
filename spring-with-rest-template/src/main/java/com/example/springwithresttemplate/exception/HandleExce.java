package com.example.springwithresttemplate.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HandleExce extends IOException {
    private  String message;


}
