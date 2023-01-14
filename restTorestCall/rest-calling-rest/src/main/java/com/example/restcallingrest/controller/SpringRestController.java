package com.example.restcallingrest.controller;

import com.example.restcallingrest.model.Person;
import com.example.restcallingrest.service.SpringRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringRestController {
    @Autowired
    private SpringRestService springRestService;

    @GetMapping(path = "/rest-person/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Person> getPerson(Long id){
        Person person=springRestService.getPerson(id);
        return  new ResponseEntity<>(person, HttpStatus.OK);
    }
}
