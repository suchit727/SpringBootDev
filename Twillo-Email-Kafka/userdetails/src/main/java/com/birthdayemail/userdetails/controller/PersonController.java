package com.birthdayemail.userdetails.controller;

import com.birthdayemail.userdetails.entity.PersonEntity;
import com.birthdayemail.userdetails.model.Person;
import com.birthdayemail.userdetails.response.PersonResponse;
import com.birthdayemail.userdetails.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class PersonController {

    @Autowired
    private final PersonService personService;

    @Autowired
    private final KafkaTemplate<String, List<PersonEntity>> kafkaTemplate;


    public PersonController(PersonService personService, KafkaTemplate<String, List<PersonEntity>> kafkaTemplate) {
        this.personService = personService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${topic.name.producer}")
    private String topicName;

    @PostMapping(path = "/persons")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person) {
        PersonResponse personResponse = personService.createPerson(person);
        return new ResponseEntity<>(personResponse, HttpStatus.CREATED);
    }


    

}
