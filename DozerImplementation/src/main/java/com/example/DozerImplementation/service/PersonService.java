package com.example.DozerImplementation.service;

import com.example.DozerImplementation.entity.AddressEntity;
import com.example.DozerImplementation.entity.PersonEntity;
import com.example.DozerImplementation.model.Address;
import com.example.DozerImplementation.model.Person;
import com.example.DozerImplementation.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class PersonService {
  @Autowired
  private PersonRepository personRepository;


    public Person getPerson(Long id) {
     Mapper mapper= new DozerBeanMapper();
     Person person=new Person();
     Optional<PersonEntity> optionalPersonEntity=personRepository.findById(id);
        if (optionalPersonEntity.isPresent()) {
            AddressEntity addressEntity = optionalPersonEntity.get().getAddressEntity();
            PersonEntity personEntity = optionalPersonEntity.get();
            person=mapper.map(personEntity,Person.class);
            Address address=mapper.map(addressEntity,Address.class);
            person.setAddress(address);
            log.info("User name {} {} ", person.getFirstName(), person.getLastName() + " retrieved");
        }
        return person;
    }
}
