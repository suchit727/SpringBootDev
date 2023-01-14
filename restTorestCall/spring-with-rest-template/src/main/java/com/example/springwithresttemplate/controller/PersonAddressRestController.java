package com.example.springwithresttemplate.controller;

import com.example.springwithresttemplate.model.Person;
import com.example.springwithresttemplate.model.PersonRe;
import com.example.springwithresttemplate.service.PersonAddressRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;

@RestController
public class PersonAddressRestController {
    @Autowired
    private PersonAddressRest personAddressRest;

    @GetMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person person = personAddressRest.getPerson(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping(path = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonRe> createPerson(@RequestBody Person person) {
        PersonRe personRe = personAddressRest.createPerson(person);
        return new ResponseEntity<>(personRe, HttpStatus.OK);
    }

    @PutMapping(path = "/update-person/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable Long id) {
        Person person1 = personAddressRest.updatePerson(id, person);
        return new ResponseEntity<>(person1, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personAddressRest.deletePerson(id);
        return ResponseEntity.ok().build();
    }
}
