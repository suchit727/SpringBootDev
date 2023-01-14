package com.crossasyst.callingservice.service;

import com.crossasyst.callingservice.exceptionhandling.RestTemplateResponseErrorHandler;
import com.crossasyst.callingservice.exceptions.BadRequestException;
import com.crossasyst.callingservice.model.Person;
import com.crossasyst.callingservice.model.PersonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Objects;

@Service
public class PersonAddressService {
    private RestTemplate restTemplate;

    PersonAddressService() {
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());  //Setting error handler for resttemplate
    }

    @Value("${resturl}")
    private String url;

    public Person getPerson(Long personId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Person> person = restTemplate.exchange(url + "/persons/" + personId, HttpMethod.GET, entity, Person.class);
        return person.getBody();
    }

    public PersonResponse createPerson(@Valid Person person) {
        HttpEntity<Person> http = new HttpEntity<>(person);
        if (Objects.isNull(person)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Null Entity provided by user");
        }
        return restTemplate.exchange(url + "/persons", HttpMethod.POST, http, PersonResponse.class).getBody();
    }
    public Person updatePerson(Long id, Person person) {
        HttpEntity<Person> httpEntity = new HttpEntity<>(person);
        if (Objects.isNull(person)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Null Entity provided by user");
        }
        return restTemplate.exchange(url + "/updateperson/" + id, HttpMethod.PUT, httpEntity, Person.class).getBody();
    }

    public void deletePerson(Long id) {
        restTemplate.delete(url + "/persons/" + id);
    }
}
