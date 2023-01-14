package com.birthdayemail.userdetails.service;

import com.birthdayemail.userdetails.entity.PersonEntity;
import com.birthdayemail.userdetails.mapper.PersonMapper;
import com.birthdayemail.userdetails.model.People;
import com.birthdayemail.userdetails.model.Person;
import com.birthdayemail.userdetails.repository.PersonRepository;
import com.birthdayemail.userdetails.response.PersonResponse;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log4j2
public class PersonService {
    @Autowired
    private final PersonRepository personRepository;

    @Autowired
    private final PersonMapper personMapper;

    @Autowired
    private final KafkaTemplate<String, JSONObject> kafkaTemplate;

    @Value("${topic.name.producer}")
    private String topicName;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper, KafkaTemplate<String, JSONObject> kafkaTemplate) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public PersonResponse createPerson(Person person) {
        PersonEntity personEntity;
        personEntity = personMapper.modelToEntity(person);
        personRepository.save(personEntity);
        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(personEntity.getId());
        log.info("Person details saved successfully");
        return personResponse;
    }

    @Scheduled(cron = "0/15 * * * * *")
    public void findAllRecords() {
        List<PersonEntity> entityList = personRepository.findAllByDayMonth();

        for (PersonEntity records : entityList) {
            People people = new People();
            people.setFirstName(records.getFirstName());
            people.setLastName(records.getLastName());
            people.setEmail_id(records.getEmailId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("People",people);

            kafkaTemplate.send(topicName, jsonObject);
        }
    }
}

