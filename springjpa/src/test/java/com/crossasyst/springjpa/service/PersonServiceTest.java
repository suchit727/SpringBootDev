package com.crossasyst.springjpa.service;


import com.crossasyst.springjpa.entity.PersonEntity;
import com.crossasyst.springjpa.model.Person;
import com.crossasyst.springjpa.repository.PersonRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonServiceTest {
    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @Before
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPerson() {
    }

    @Test
    void getPerson() {
       /* when(personRepository.getPerson(1L)).thenReturn(new PersonEntity(1l,"pranay","khadtar","mumbai","400066"));
        Person personEntity=personService.getPerson(1L);
        assertEquals("pranay", personEntity.getFirstName());
        assertEquals("khadtar", personEntity.getLastName());
        assertEquals("mumbai", personEntity.getCity());
        assertEquals("400066", personEntity.getZipCode());
    */
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }
}