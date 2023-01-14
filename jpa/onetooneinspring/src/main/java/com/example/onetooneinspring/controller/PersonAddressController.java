package com.example.onetooneinspring.controller;

import com.example.onetooneinspring.entity.PersonEntity;
import com.example.onetooneinspring.model.Address;
import com.example.onetooneinspring.model.Person;
import com.example.onetooneinspring.model.PersonResponse;
import com.example.onetooneinspring.service.PersonAddressService;
import lombok.extern.log4j.Log4j2;
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

import java.util.List;
import java.util.Objects;

@RestController
@Log4j2

public class PersonAddressController {
    @Autowired
    private PersonAddressService personAddressService;

    @PostMapping(path = "/persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person) {
        PersonResponse personResponse = personAddressService.createPerson(person);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/persons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPersons(@PathVariable Long id) {
        Person person = personAddressService.getPerson(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }


    @PutMapping(path = "/updatepersons/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        personAddressService.updatePerson(id, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/persons/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personAddressService.deletePerson(id);
        return ResponseEntity.ok().build();
    }
//    @GetMapping(path = "/pers")
}
