package com.example.jpapractice.service;

import com.example.jpapractice.entity.PersonEntity;
import com.example.jpapractice.mapper.PersonMapper;
import com.example.jpapractice.model.Person;
import com.example.jpapractice.model.PersonResponse;
import com.example.jpapractice.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    public PersonResponse createPerson(Person person) {
        PersonEntity personEntity = null;
        personEntity = personMapper.personToEntity(person);
        PersonResponse personResponse = new PersonResponse();
        Long id = (long) (Math.random() * 20);
        personEntity.setId(id);
        personResponse.setId(personEntity.getId());
        personRepository.save(personEntity);
        return personResponse;
    }

    public Person getPerson(Long personId) {
        Person person = new Person();
        Optional<PersonEntity> personEntityOptional = personRepository.findById(personId);
        if (personEntityOptional.isPresent()) {
            person = personMapper.entityToPerson(personEntityOptional.get());
        } else {
            log.info("Person id {}",personId + "Not found");
        }
        return person;
    }

    public void updatePerson(Long personId, Person person) {
        Optional<PersonEntity> personEntityOptional=personRepository.findById(personId);
        if (personEntityOptional.isPresent()){
//                personMapper.personToEntity(person);
//            personEntityOptional.get().setLastName(person.getLastName());
            PersonEntity personEntity = personEntityOptional.get();
            personEntity.setFirstName(person.getFirstName());
            personRepository.save(personEntityOptional.get());
        }else{

            log.info("Person id {} ",personId+"not found");
        }
    }

    public void deletePerson(Long personId) {
        Optional<PersonEntity> personEntityOptional = personRepository.findById(personId);
        if(personEntityOptional.isPresent()){
            personRepository.deleteById(personId);
        }else {
            log.info("Person id {} ",personId+"not found");
        }
    }
}


