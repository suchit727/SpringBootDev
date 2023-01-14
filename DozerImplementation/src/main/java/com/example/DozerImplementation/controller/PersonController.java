package com.example.DozerImplementation.controller;

import com.example.DozerImplementation.model.Person;
import com.example.DozerImplementation.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(path = "/person/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        Person person=personService.getPerson(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

}
