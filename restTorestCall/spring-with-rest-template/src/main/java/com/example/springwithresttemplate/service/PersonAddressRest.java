package com.example.springwithresttemplate.service;

import com.example.springwithresttemplate.model.Person;
import com.example.springwithresttemplate.model.PersonRe;

public interface PersonAddressRest {
    Person getPerson(Long id);

    PersonRe createPerson(Person person);

    Person updatePerson(Long id, Person person);

    void deletePerson(Long id);
}
