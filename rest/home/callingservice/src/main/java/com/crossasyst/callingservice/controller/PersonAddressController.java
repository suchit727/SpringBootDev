package com.crossasyst.callingservice.controller;

import com.crossasyst.callingservice.model.Person;
import com.crossasyst.callingservice.model.PersonResponse;
import com.crossasyst.callingservice.service.PersonAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class PersonAddressController {
    @Autowired
    private PersonAddressService personAddressService;

    @GetMapping(path = "/persons/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person person = personAddressService.getPerson(id);
        return new ResponseEntity<>(person, HttpStatus.FOUND);
    }

    @PostMapping(path = "/persons")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person) {
        PersonResponse personResponse = personAddressService.createPerson(person);
        return new ResponseEntity<>(personResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        personAddressService.updatePerson(id, person);
        return new ResponseEntity<>(person, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "persons/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personAddressService.deletePerson(id);
        return ResponseEntity.ok().body("Deleted Successfully");
    }

}
