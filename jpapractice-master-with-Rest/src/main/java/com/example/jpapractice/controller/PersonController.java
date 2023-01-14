package com.example.jpapractice.controller;

import com.example.jpapractice.model.Person;
import com.example.jpapractice.model.PersonResponse;
import com.example.jpapractice.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Objects;

@RestController
@Log4j2
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping(path = "/persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person) {
        PersonResponse personResponse = personService.createPerson(person);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/persons/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable Long personId) {
        Person person = personService.getPerson(personId);
        if (!(Objects.isNull(person))) {
            log.info("Person with personId {} ", personId + "Found");
        } else {
            log.info("Person id {} ", personId + "not Found");
            ResponseEntity.noContent().build();

        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping(path = "/persons/{personId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable Long personId, @RequestBody Person person) {

        if (!(Objects.isNull(person))) {
            personService.updatePerson(personId, person);
        } else {
            log.info("Person with personId {}", personId + "Not Found");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();

    }

    @DeleteMapping(path = "/persons/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
        return ResponseEntity.ok().build();
    }
}