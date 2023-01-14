package com.crossasyst.springjpa.controller;

import com.crossasyst.springjpa.model.Person;
import com.crossasyst.springjpa.model.PersonResponse;
import com.crossasyst.springjpa.service.PersonService;
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

import java.util.Objects;

@RestController
@Log4j2
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping(path = "/persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person) {

        PersonResponse personResponse = null;
        if ((Objects.isNull(person)){
            log.info("Person name {} {} can be created", person.getFirstName(), person.getLastName());
            personResponse = personService.createPerson(person);


        }else {
            log.info("Person name {} {} cannot be created", person.getFirstName(), person.getLastName());
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/persons/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable Long personId) {
        Person person ;
        if (Objects.isNull(personId)) {

            log.info("Person id {} ", personId + "not Found");
            return  ResponseEntity.noContent().build();
        }else {
            log.info("Person id {} ", personId + "Found");
            person = personService.getPerson(personId);
        }

        return new ResponseEntity<>(person, HttpStatus.OK);

    }
    @PutMapping(path = "/persons/{personId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable Long personId, @RequestBody Person person) {

        if (Objects.isNull(person)) {

            log.info("Person name {} {} cannot be updated", person.getFirstName(), person.getLastName());
            return ResponseEntity.noContent().build();
        }else {
            personService.updatePerson(personId, person);
        }
        return ResponseEntity.ok().build();

    }
    @DeleteMapping(path = "/persons/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
        return ResponseEntity.ok().build();
    }
}
