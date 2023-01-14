package com.crossasyst.springdocker.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class DockerController {
    @GetMapping(path="/pings",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getData(){
        log.info("Status Ok");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
