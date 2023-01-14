package com.example.springwithresttemplate.service;

import com.example.springwithresttemplate.client.PersonAddressClient;
import com.example.springwithresttemplate.model.Address;
import com.example.springwithresttemplate.model.Person;
import com.example.springwithresttemplate.model.PersonRe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PersonAddressRestImplTest {
    @InjectMocks
    private PersonAddressRestImpl sut;
    @Mock
    private PersonAddressClient personAddressClient;

    private Person person;
    private Address address;
    private Long id;

    @BeforeEach
    public  void setUp(){
        MockitoAnnotations.openMocks(this);
        id=1L;
        person =new Person();
        person.setFirstName("firstname");
        person.setLastName("lastname");

        address=new Address();
        address.setAddressOne("address one");
        address.setAddressTwo("address two");
        address.setCity("city");
        address.setState("state");
        address.setZipCode("zipcode");
        person.setAddress(address);
    }
    @Test
    public void testGetPerson(){
        ArgumentCaptor<Long> personArgumentCaptor=ArgumentCaptor.forClass(Long.class);
        when(personAddressClient.getPerson(id)).thenReturn(person);

        Person person1=sut.getPerson(id);

        assertEquals("firstname",person1.getFirstName());
        assertEquals("lastname",person1.getLastName());
        assertEquals("address one",person1.getAddress().getAddressOne());
        assertEquals("address two",person1.getAddress().getAddressTwo());
        assertEquals("city",person1.getAddress().getCity());
        assertEquals("state",person1.getAddress().getState());
        assertEquals("zipcode",person1.getAddress().getZipCode());
        verify(personAddressClient,times(1)).getPerson(anyLong());
        verify(personAddressClient).getPerson(personArgumentCaptor.capture());
        Long id1=personArgumentCaptor.getValue();
        assertEquals(1L,id1);
    }
    @Test
    public void testUpdatePerson(){
        ArgumentCaptor<Person> personArgumentCaptor=ArgumentCaptor.forClass(Person.class);
     when(personAddressClient.updatePerson(person,id)).thenReturn(person);
     Person person1=sut.updatePerson(id,person);

        assertEquals("firstname",person1.getFirstName());
        assertEquals("lastname",person1.getLastName());
        assertEquals("address one",person1.getAddress().getAddressOne());
        assertEquals("address two",person1.getAddress().getAddressTwo());
        assertEquals("city",person1.getAddress().getCity());
        assertEquals("state",person1.getAddress().getState());
        assertEquals("zipcode",person1.getAddress().getZipCode());

   verify(personAddressClient,times(1)).updatePerson(person,id);
   verify(personAddressClient).updatePerson(personArgumentCaptor.capture(),anyLong());
   Person newPerson=personArgumentCaptor.getValue();
        assertEquals("firstname",newPerson.getFirstName());

    }

    @Test
    public void  testDeletePerson()
    {
        Long perId=1L;
        ArgumentCaptor<Long> longArgumentCaptor=ArgumentCaptor.forClass(Long.class);
        doNothing().when(personAddressClient).deletePerson(perId);
        sut.deletePerson(perId);
        verify(personAddressClient,times(1)).deletePerson(perId);
        verify(personAddressClient).deletePerson(longArgumentCaptor.capture());
        Long id1=longArgumentCaptor.getValue();
        System.out.println(id1);
        assertEquals(1L,id1);


    }
/*
    @Test
    public void testCreatePerson(){
        PersonRe personRe=new PersonRe();
        personRe.setId(1L);
        when(personAddressClient.createPerson(person)).thenReturn(personRe);
        PersonRe sutPersonRe=sut.createPerson(person);
        assertEquals(1L,sutPersonRe.getId());
        verify(personAddressClient,times(1)).createPerson(person);
    }*/


    @Test
    public void testCreatePerson(){
        PersonRe personRe=new PersonRe();
        personRe.setId(1L);
        ArgumentCaptor<Person> personArgumentCaptor=ArgumentCaptor.forClass(Person.class);
        when(personAddressClient.createPerson(person)).thenReturn(personRe);
        PersonRe sutPersonRe=sut.createPerson(person);
        assertEquals(1L,sutPersonRe.getId());
        verify(personAddressClient,times(1)).createPerson(person);
        verify(personAddressClient).createPerson(personArgumentCaptor.capture());
//        Person result = personArgumentCaptor.getValue();
//        assertEquals(person,result);



    }


}
