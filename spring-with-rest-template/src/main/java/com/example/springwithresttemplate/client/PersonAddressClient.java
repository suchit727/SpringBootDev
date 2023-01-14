package com.example.springwithresttemplate.client;

import com.example.springwithresttemplate.exception.NotFoundException;
import com.example.springwithresttemplate.exception.PersonAddressBadRequestException;
import com.example.springwithresttemplate.exception.PersonAddressClientException;
import com.example.springwithresttemplate.model.Person;
import com.example.springwithresttemplate.model.PersonRe;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Log4j2
public class PersonAddressClient {
    @Autowired
    private RestTemplate restTemplate;

     @Value("${new.url}")
    private String rightUrl;

    private  String GET_USER_BY_USER_ID= "http://one-to-one/persons/{id}";

//    public Person getPerson(Long id) {
//        try {
//
//            return restTemplate.exchange(rightUrl + "/persons/" + id, HttpMethod.GET, HttpEntity.EMPTY, Person.class).getBody();
//
//            //return restTemplate.getForEntity(rightUrl + "/persons/" + id, Person.class).getBody();
//        } catch (HttpClientErrorException | HttpServerErrorException e) {
//            String message;
//            if (e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
//                message = String.format("No person found for=%s", id);
//                log.error(message, e);
//                throw new NotFoundException(message);
//            }
//            message = String.format("Error when trying to retrieve person id=%s", id);
//            log.error(message);
//            throw new PersonAddressClientException(message);
//        }
//
//    }
    public Person getPerson(Long id) {
        try {
            final URI uri= UriComponentsBuilder.fromUriString(GET_USER_BY_USER_ID).build(id);
            log.info(uri);

            return restTemplate.exchange(uri,HttpMethod.GET, HttpEntity.EMPTY, Person.class).getBody();
            //return restTemplate.getForEntity(rightUrl + "/persons/" + id, Person.class).getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String message;
            if (e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
                message = String.format("No person found for=%s", id);
                log.error(message, e);
                throw new NotFoundException(message);
            }
            message = String.format("Error when trying to retrieve person id=%s", id);
            log.error(message);
            throw new PersonAddressClientException(message);
        }

    }
    public PersonRe createPerson(Person person) {
        try {

            HttpEntity<Person> http = new HttpEntity<>(person);
            ResponseEntity<PersonRe> personRe = restTemplate.exchange(rightUrl + "/persons", HttpMethod.POST, http, PersonRe.class);
            return personRe.getBody();
            /*return restTemplate.exchange(rightUrl+"/persons", HttpMethod.POST,http,PersonRe.class).getBody();
             */
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String message;
            if (HttpStatus.BAD_REQUEST.equals(e.getStatusCode())) {
                message = "It's Bad Request !!!";
                log.error(message, e);
                throw new PersonAddressBadRequestException(message);

            }
            message = "Unable to save body";
            log.error(message);
            throw new PersonAddressClientException("Error when trying to create person", e);
        }

    }

    public Person updatePerson(Person person, Long id) {
        try {
            HttpEntity<Person> http = new HttpEntity<>(person);
            return restTemplate.exchange(rightUrl + "/updatepersons/" + id, HttpMethod.PUT, http, Person.class).getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String message;
            if (e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
                message = String.format("No person found for=%s", id);
                log.error(message, e);
                throw new NotFoundException(message);

            }
            message = String.format("Unable to update the body with person_id=%s", id);
            log.error(message);
            throw new PersonAddressClientException(message, e);
        }
    }

    public void deletePerson(Long id) {
        try {
            restTemplate.delete(rightUrl + "/persons/{id}", id);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.warn("Unable to delete Person for id={}", id);
            throw new PersonAddressClientException("Error when trying to delete person id= " + id);
        }


    }


}
