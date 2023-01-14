package com.example.restcallingrest.client;

import com.example.restcallingrest.exception.NotFoundException;
import com.example.restcallingrest.exception.PersonAddressClientException;
import com.example.restcallingrest.model.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Component
@Log4j2
public class SpringRestClient {
 @Autowired
 private RestTemplate restTemplate;
    @Value("${new.url}")
    private String rightUrl;
    public Person getPerson(Long id) {
        try{
          return restTemplate.exchange(rightUrl+"/person/"+id, HttpMethod.GET, HttpEntity.EMPTY,Person.class).getBody();
        }catch (HttpClientErrorException | HttpServerErrorException e){
          String message;
      if(e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()){
          message = String.format("No person found for=%s", id);
          log.error(message, e);
          throw new NotFoundException(message);
      }
            message = String.format("Error when trying to retrieve person id=%s", id);
            log.error(message);
            throw new PersonAddressClientException(message);
        }

    }
}
