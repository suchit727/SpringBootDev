package com.example.restcallingrest.service;

import com.example.restcallingrest.client.SpringRestClient;
import com.example.restcallingrest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringRestServiceImpl implements SpringRestService{

    @Autowired
    private SpringRestClient springRestClient;

   @Override
    public Person getPerson(Long id){
       Person person=springRestClient.getPerson(id);
       return person;
   }



}
