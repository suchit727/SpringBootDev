package com.crossasyst.springjpa.service;

import com.crossasyst.springjpa.entity.PersonEntity;
import com.crossasyst.springjpa.mapper.PersonMapper;
import com.crossasyst.springjpa.model.Person;
import com.crossasyst.springjpa.model.PersonResponse;
import com.crossasyst.springjpa.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private  PersonMapper personMapper;

    public PersonResponse createPerson(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity = personMapper.personToEntity(person);
        PersonResponse personResponse = new PersonResponse();
        Long id = (long) (Math.random() * 20);
        personResponse.setId(id);
        personRepository.save(personEntity);
        return personResponse;
    }
    public Person getPerson(Long personId){
        Optional<PersonEntity> personEntityOptional=personRepository.findById(personId);
        Person person=new Person();

        if(personEntityOptional.isPresent()){
         person=personMapper.entityToPerson(personEntityOptional.get());
        }else{
          log.info("Person id {} not found",personId);
        }
        return person;
    }
    public void updatePerson(Long personId, Person person) {
        Optional<PersonEntity> personEntityOptional=personRepository.findById(personId);
        if (personEntityOptional.isPresent()){
            /*PersonEntity personEntity = personEntityOptional.get();*/
                    personEntityOptional.get().setLastName(person.getLastName());
            personEntityOptional.get().setFirstName(person.getFirstName());
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
