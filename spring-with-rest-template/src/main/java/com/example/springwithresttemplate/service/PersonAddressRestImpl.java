package com.example.springwithresttemplate.service;

import com.example.springwithresttemplate.client.PersonAddressClient;
import com.example.springwithresttemplate.model.Person;
import com.example.springwithresttemplate.model.PersonRe;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonAddressRestImpl implements PersonAddressRest{

@Autowired
private RestTemplate restTemplate;

    @Autowired
    private PersonAddressClient personAddressClient;


    @Override
    public Person getPerson(Long id) {

        Person person= personAddressClient.getPerson(id);
        return person;
    }
    @Override
    public PersonRe createPerson(Person person){

        PersonRe personRe=personAddressClient.createPerson(person);
        return personRe;

    }
    @Override
    public Person updatePerson(Long id,Person person){
    return personAddressClient.updatePerson(person,id);
    }

    @Override
    public void deletePerson(Long id){
        personAddressClient.deletePerson(id);
    }
}
