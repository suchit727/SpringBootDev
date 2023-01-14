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
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

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

    private  String oneToOne= "http://one-to-one";


    @Mock
    private RestTemplate restTemplate;

    private Person person;

    private Address address;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(sut,"rightUrl",oneToOne);


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
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.when(restTemplate.exchange(argumentCaptor.capture(), Mockito.eq(HttpMethod.GET), Mockito.eq(HttpEntity.EMPTY), Mockito.eq(Person.class))).thenReturn(responseEntity);
        Person person1 = sut.getPerson(1l);
        assertEquals("suchit", person1.getFirstName());

        Mockito.verify(restTemplate, Mockito.times(1)).exchange(argumentCaptor.capture(), Mockito.any(), Mockito.any(), Mockito.eq(Person.class));
        assertEquals("http://one-to-one/persons/1",argumentCaptor.getValue());
        assertEquals( person,person1);
        assertNotNull(person1);
    }

    //get method server exception
    @Test
    public void testGetPersonServerException(){
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        PersonAddressClientException thrown = Assertions.assertThrows(PersonAddressClientException.class,()->{
            Mockito.when(restTemplate.exchange(argumentCaptor.capture(), Mockito.eq(HttpMethod.GET), Mockito.eq(HttpEntity.EMPTY), Mockito.eq(Person.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
            sut.getPerson(1L);
            Mockito.verify(restTemplate, Mockito.times(1)).exchange(argumentCaptor.capture(), Mockito.any(), Mockito.any(), Mockito.eq(Person.class));
            assertEquals( "http://one-to-one/persons/1",argumentCaptor.getValue());
        });

       assertEquals("Error when trying to retrieve person id=1",thrown.getMessage());
    }
    @Test
    public void testGetServerException(){
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        NotFoundException thrown=Assertions.assertThrows(NotFoundException.class,()->{
            Mockito.when(restTemplate.exchange(argumentCaptor.capture(), Mockito.eq(HttpMethod.GET), Mockito.eq(HttpEntity.EMPTY), Mockito.eq(Person.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.NOT_FOUND));
            sut.getPerson(1L);
            Mockito.verify(restTemplate, Mockito.times(1)).exchange(argumentCaptor.capture(), Mockito.any(), Mockito.any(), Mockito.eq(Person.class));
        });
        assertEquals( "http://one-to-one/persons/1",argumentCaptor.getValue());
        assertEquals("No person found for=1",thrown.getMessage());
    }




    //Get client Exception
    @Test
    public void testGetPersonClientException(){
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        PersonAddressClientException thrown = Assertions.assertThrows(PersonAddressClientException.class,()->{
            Mockito.when(restTemplate.exchange(argumentCaptor.capture(), Mockito.eq(HttpMethod.GET), Mockito.eq(HttpEntity.EMPTY), Mockito.eq(Person.class)))
                    .thenThrow(new HttpClientErrorException(HttpStatus.CONFLICT));
            sut.getPerson(1L);
            Mockito.verify(restTemplate, Mockito.times(1)).exchange(argumentCaptor.capture(), Mockito.any(), Mockito.any(), Mockito.eq(Person.class));
        });
        assertEquals( "http://one-to-one/persons/1",argumentCaptor.getValue());
        assertEquals("Error when trying to retrieve person id=1",thrown.getMessage());
    }
    @Test
    public void testGetClientException(){
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        NotFoundException thrown=Assertions.assertThrows(NotFoundException.class,()->{
            Mockito.when(restTemplate.exchange(argumentCaptor.capture(), Mockito.eq(HttpMethod.GET), Mockito.eq(HttpEntity.EMPTY), Mockito.eq(Person.class)))
                    .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND,"This is not found"));
            sut.getPerson(1L);
            Mockito.verify(restTemplate, Mockito.times(1)).exchange(argumentCaptor.capture(), Mockito.any(), Mockito.any(), Mockito.eq(Person.class));
        });
        assertEquals( "http://one-to-one/persons/1",argumentCaptor.getValue());
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

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        ResponseEntity<PersonRe> responseEntity = ResponseEntity.ok(personRe);
        when(restTemplate.exchange(argumentCaptor.capture(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class))).thenReturn(responseEntity);
        PersonRe responsePerson = sut.createPerson(person);
        assertEquals( "http://one-to-one/persons",argumentCaptor.getValue());
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
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        PersonAddressBadRequestException thrown=Assertions.assertThrows(PersonAddressBadRequestException.class,()->{
            when(restTemplate.exchange(argumentCaptor.capture(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.BAD_REQUEST));
            sut.createPerson(person);
        });

        assertEquals( "http://one-to-one/persons",argumentCaptor.getValue());
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

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            when(restTemplate.exchange(argumentCaptor.capture(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.CONFLICT));
            sut.createPerson(person);
        });
        assertEquals( "http://one-to-one/persons",argumentCaptor.getValue());
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

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        PersonAddressBadRequestException thrown=Assertions.assertThrows(PersonAddressBadRequestException.class,()->{
            when(restTemplate.exchange(argumentCaptor.capture(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class)))
                    .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
            sut.createPerson(person);
        });
        assertEquals( "http://one-to-one/persons",argumentCaptor.getValue());
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

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            when(restTemplate.exchange(argumentCaptor.capture(), eq(HttpMethod.POST), any(HttpEntity.class), eq(PersonRe.class)))
                    .thenThrow(new HttpClientErrorException(HttpStatus.CONFLICT));
            sut.createPerson(person);
        });
        assertEquals( "http://one-to-one/persons",argumentCaptor.getValue());
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

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        when(restTemplate.exchange(argumentCaptor.capture(), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Person.class))).thenReturn(ResponseEntity.ok(person));
        Person responsePerson = sut.updatePerson(person, 1L);
        assertEquals("suchit", person.getFirstName());
        assertEquals("khadtar", person.getLastName());

        assertEquals("Borivali", responsePerson.getAddress().getAddressOne());

        verify(restTemplate, Mockito.times(1)).exchange(argumentCaptor.capture(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(Person.class));
        assertEquals( "http://one-to-one/updatepersons/1",argumentCaptor.getValue());


    }


    //Put Server
    @Test
    public void updatePersonNotFound(){

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        NotFoundException thrown=Assertions.assertThrows(NotFoundException.class,()->{
            when(restTemplate.exchange(argumentCaptor.capture(),eq(HttpMethod.PUT),eq(HttpEntity.EMPTY),eq(Person.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.NOT_FOUND));
            sut.updatePerson(person,1L);
        });
        assertEquals( "http://one-to-one/updatepersons/1",argumentCaptor.getValue());
        assertEquals("No person found for=1",thrown.getMessage());
    }
    @Test
    public void updatePersonConflict(){

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            when(restTemplate.exchange(argumentCaptor.capture(),eq(HttpMethod.PUT),eq(HttpEntity.EMPTY),eq(Person.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.CONFLICT));
            sut.updatePerson(person,1L);
        });
        assertEquals( "http://one-to-one/updatepersons/1",argumentCaptor.getValue());
      assertEquals("Unable to update the body with person_id=1",thrown.getMessage());
    }



    //Put Client
    @Test
    public void updatePersonNotFoundByClient(){

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        NotFoundException thrown=Assertions.assertThrows(NotFoundException.class,()->{
            when(restTemplate.exchange(argumentCaptor.capture(),eq(HttpMethod.PUT),eq(HttpEntity.EMPTY),eq(Person.class)))
                    .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
            sut.updatePerson(person,1L);
        });
        assertEquals( "http://one-to-one/updatepersons/1",argumentCaptor.getValue());
        assertEquals("No person found for=1",thrown.getMessage());
    }
    @Test
    public void updatePersonConflictByClient(){

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            when(restTemplate.exchange(argumentCaptor.capture(),eq(HttpMethod.PUT),eq(HttpEntity.EMPTY),eq(Person.class)))
                    .thenThrow(new HttpClientErrorException(HttpStatus.CONFLICT));
            sut.updatePerson(person,1L);
        });
        assertEquals( "http://one-to-one/updatepersons/1",argumentCaptor.getValue());
        assertEquals("Unable to update the body with person_id=1",thrown.getMessage());
    }


    @Test
    public void testDeletePerson() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        doNothing().when(restTemplate).delete(argumentCaptor.capture(), anyLong());
        sut.deletePerson(4L);
        verify(restTemplate, Mockito.times(1)).delete(argumentCaptor.capture(), anyLong());

    }

    //delete server
    @Test
    public void testDeletePersonServerError() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            doThrow(new HttpServerErrorException(HttpStatus.CONFLICT,"internal server error")).when(restTemplate).delete(argumentCaptor.capture(),anyLong());
            sut.deletePerson(1L);
        });

    }
    //delete client
    @Test
    public void testDeletePersonClientError(){
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        PersonAddressClientException thrown=Assertions.assertThrows(PersonAddressClientException.class,()->{
            doThrow(new HttpClientErrorException(HttpStatus.CONFLICT,"internal server error")).when(restTemplate).delete(argumentCaptor.capture(),anyLong());
            sut.deletePerson(1L);
        });
    }
}

