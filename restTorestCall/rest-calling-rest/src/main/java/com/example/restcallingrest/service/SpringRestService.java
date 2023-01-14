package com.example.restcallingrest.service;

import com.example.restcallingrest.model.Person;
import org.springframework.stereotype.Service;


public interface SpringRestService{
    Person getPerson(Long id);
}
