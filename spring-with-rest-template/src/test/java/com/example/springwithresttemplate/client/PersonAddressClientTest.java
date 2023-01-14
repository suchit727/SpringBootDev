package com.example.springwithresttemplate.client;

import com.example.springwithresttemplate.exception.NotFoundException;
import com.example.springwithresttemplate.exception.PersonAddressBadRequestException;
import com.example.springwithresttemplate.exception.PersonAddressClientException;
import com.example.springwithresttemplate.model.Address;
import com.example.springwithresttemplate.model.Person;
import com.example.springwithresttemplate.model.PersonRe;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
@Log4j2

public class PersonAddressClientTest {

    @InjectMocks
    private PersonAddressClient sut;


    @Mock
    private RestTemplate restTemplate;

    private Person person;

    private Address address;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


    }

    @Test
    public void testGetPerson() {
        person = new Person();
        person.setFirstName("suchit");
        person.setLastName("khadtar");

        address = new Address();
        address.setAddressOne("address one");
        address.setAddressTwo("address two");
        address.setCity("city");
        address.setState("state");
        address.setZipCode("zipcode");
        person.setAddress(address);

        ResponseEntity<Person> responseEntity = ResponseEntity.ok(person);
        Mockito.when(restTemplate.exchange(Mockito.any(URI.class), Mockito.eq(HttpMethod.GET), Mockito.eq(HttpEntity.EMPTY), Mockito.eq(Person.class))).thenReturn(responseEntity);
        Person person1 = sut.getPerson(1L);
        assertEquals("suchit", person1.getFirstName());

        Mockito.verify(restTemplate, Mockito.times(1)).exchange(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.eq(Person.class));

        assertEquals( person,person1);
        assertNotNull(person1);
    }

    //get method server exception
    @Test
    public void testGetPersonServerException(){
        PersonAddressClientException thrown = Assertions.assertThrows(PersonAddressClientException.class,()->{
            Mockito.when(restTemplate.exchange(Mockito.any(URI.class), Mockito.eq(HttpMethod.GET), Mockito.eq(HttpEntity.EMPTY), Mockito.eq(Person.class))).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
            sut.getPerson(1L);
            Mockito.verify(restTemplate, Mockito.times(1)).exchange(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.eq(Person.class));
        });
       assertEquals("Error when trying to retrieve person id=1",thrown.getMessage());
    }
    @Test
    public void testGetServerException(){
        NotFoundException thrown=Assertions.assertThrows(NotFoundException.class,()->{
            Mockito.when(restTemplate.exchange(Mockito.any(URI.class), Mockito.eq(HttpMethod.GET), Mockito.eq(HttpEntity.EMPTY), Mockito.eq(Person.class))).thenThrow(new HttpServerErrorException(HttpStatus.NOT_FOUND));
            sut.getPerson(1L);
            Mockito.verify(restTemplate, Mockito.times(1)).exchange(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.eq(Person.class));
        });
        assertEquals("No person found for=1",thrown.getMessage());
    }




    //Get client Exception
    @Test
    public void testGetPersonClientException(){
        PersonAddressClientException thrown = Assertions.assertThrows(PersonAddressClientException.class,()->{
            when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(Person.class))).thenThrow(new HttpClientErrorException(HttpStatus.CONFLICT));
            sut.getPerson(1L);
            Mockito.verify(restTemplate, Mockito.times(1)).exchange(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.eq(Person.class));
        });
        assertEquals("Error when trying to retrieve person id=1",thrown.getMessage());
    }
    @Test
    public void testGetClientException(){
        NotFoundException thrown=Assertions.assertThrows(NotFoundException.class,()->{
            when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(Person.class))).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND,"This is not found"));
            sut.getPerson(1L);
            Mockito.verify(restTemplate, Mockito.times(1)).exchange(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.eq(Person.class));
        });
        assertEquals("No person found for=1",thrown.getMessage());
    }




    @Test
    public void testCreatePerson() {
        Person person = new Person();
        person.setFirstName("suchit");
        person.setLastName("khadtar");

        Address address = new Address();
        address.setAddressOne("address one");
        address.setAddressTwo("address two");
        address.setCity("city");
        address.setState("state");
        address.setZipCode("zipcode");
        person.setAddress(address);
        PersonRe personRe = new PersonRe(1L);


        ResponseEntity<PersonRe> responseEntity = ResponseEntity.ok(personRe);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class))).thenReturn(responseEntity);
        PersonRe responsePerson = sut.createPerson(person);

        assertEquals(1L, responsePerson.getId());
        verify(restTemplate, Mockito.times(1)).exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class));

    }


    //Post Server error
    @Test
    public void createPersonBadRequest(){
        person = new Person();
        person.setFirstName("suchit");
        person.setLastName("khadtar");

        address = new Address();
        address.setAddressOne("address one");
        address.setAddressTwo("address two");
        address.setCity("city");
        address.setState("state");
        address.setZipCode("zipcode");
        person.setAddress(address);


        PersonAddressBadRequestException thrown=Assertions.assertThrows(PersonAddressBadRequestException.class,()->{
            when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.BAD_REQUEST));
            sut.createPerson(person);
        });
        assertEquals("It's Bad Request !!!",thrown.getMessage());

    }


    @Test
    public void createPersonServer(){
        person = new Person();
        person.setFirstName("suchit");
        person.setLastName("khadtar");

        address = new Address();
        address.setAddressOne("address one");
        address.setAddressTwo("address two");
        address.setCity("city");
        address.setState("state");
        address.setZipCode("zipcode");
        person.setAddress(address);


        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.CONFLICT));
            sut.createPerson(person);
        });
        assertEquals("Error when trying to create person",thrown.getMessage());

    }

    //Post Client Error
    @Test
    public void createPersonClientException(){
        person = new Person();
        person.setFirstName("suchit");
        person.setLastName("khadtar");

        address = new Address();
        address.setAddressOne("address one");
        address.setAddressTwo("address two");
        address.setCity("city");
        address.setState("state");
        address.setZipCode("zipcode");
        person.setAddress(address);


        PersonAddressBadRequestException thrown=Assertions.assertThrows(PersonAddressBadRequestException.class,()->{
            when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class)))
                    .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
            sut.createPerson(person);
        });
        assertEquals("It's Bad Request !!!",thrown.getMessage());

    }


    @Test
    public void createPersonClient(){
        person = new Person();
        person.setFirstName("suchit");
        person.setLastName("khadtar");

        address = new Address();
        address.setAddressOne("address one");
        address.setAddressTwo("address two");
        address.setCity("city");
        address.setState("state");
        address.setZipCode("zipcode");
        person.setAddress(address);


        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class)))
                    .thenThrow(new HttpClientErrorException(HttpStatus.CONFLICT));
            sut.createPerson(person);
        });
        assertEquals("Error when trying to create person",thrown.getMessage());

    }









    @Test
    public void testUpdatePerson() {

        person = new Person();
        person.setFirstName("suchit");
        person.setLastName("khadtar");

        address = new Address();
        address.setAddressOne("Borivali");
        address.setAddressTwo("near national park");
        address.setCity("Mumbai");
        address.setState("Maharashtra");
        address.setZipCode("400066");

        person.setAddress(address);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Person.class))).thenReturn(ResponseEntity.ok(person));
        Person responsePerson = sut.updatePerson(person, 1L);
        assertEquals("suchit", person.getFirstName());
        assertEquals("khadtar", person.getLastName());

        assertEquals("Borivali", responsePerson.getAddress().getAddressOne());
        verify(restTemplate, Mockito.times(1)).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(Person.class));


    }


    //Put Server
    @Test
    public void updatePersonNotFound(){


        NotFoundException thrown=Assertions.assertThrows(NotFoundException.class,()->{
            when(restTemplate.exchange(anyString(),eq(HttpMethod.PUT),eq(HttpEntity.EMPTY),eq(Person.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.NOT_FOUND));
            sut.updatePerson(person,1L);
        });
        assertEquals("No person found for=1",thrown.getMessage());
    }
    @Test
    public void updatePersonConflict(){


        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            when(restTemplate.exchange(anyString(),eq(HttpMethod.PUT),eq(HttpEntity.EMPTY),eq(Person.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.CONFLICT));
            sut.updatePerson(person,1L);
        });
      assertEquals("Unable to update the body with person_id=1",thrown.getMessage());
    }



    //Put Client
    @Test
    public void updatePersonNotFoundByClient(){


        NotFoundException thrown=Assertions.assertThrows(NotFoundException.class,()->{
            when(restTemplate.exchange(anyString(),eq(HttpMethod.PUT),eq(HttpEntity.EMPTY),eq(Person.class)))
                    .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
            sut.updatePerson(person,1L);
        });
        assertEquals("No person found for=1",thrown.getMessage());
    }
    @Test
    public void updatePersonConflictByClient(){


        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            when(restTemplate.exchange(anyString(),eq(HttpMethod.PUT),eq(HttpEntity.EMPTY),eq(Person.class)))
                    .thenThrow(new HttpClientErrorException(HttpStatus.CONFLICT));
            sut.updatePerson(person,1L);
        });
        assertEquals("Unable to update the body with person_id=1",thrown.getMessage());
    }


    @Test
    public void testDeletePerson() {
        doNothing().when(restTemplate).delete(Mockito.anyString(), anyLong());
        sut.deletePerson(1L);
        verify(restTemplate, Mockito.times(1)).delete(Mockito.anyString(), anyLong());
    }

    //delete server
    @Test
    public void testDeletePersonServerError() {
        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            doThrow(new HttpServerErrorException(HttpStatus.CONFLICT,"internal server error")).when(restTemplate).delete(Mockito.anyString(),anyLong());
            sut.deletePerson(1L);
        });

    }
    //delete client
    @Test
    public void testDeletePersonClientError(){
        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            doThrow(new HttpServerErrorException(HttpStatus.CONFLICT,"internal server error")).when(restTemplate).delete(Mockito.anyString(),anyLong());
            sut.deletePerson(1L);
        });
    }
}

