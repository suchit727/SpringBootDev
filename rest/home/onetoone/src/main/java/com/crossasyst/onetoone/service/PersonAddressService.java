package com.crossasyst.onetoone.service;

import com.crossasyst.onetoone.entities.AddressEntity;
import com.crossasyst.onetoone.entities.PersonEntity;
import com.crossasyst.onetoone.exceptionhandling.PersonNotFoundException;
import com.crossasyst.onetoone.exceptionhandling.UnexpectedException;
import com.crossasyst.onetoone.mapper.AddressMapper;
import com.crossasyst.onetoone.mapper.PersonMapper;
import com.crossasyst.onetoone.model.Address;
import com.crossasyst.onetoone.model.PatchRequest;
import com.crossasyst.onetoone.model.Person;
import com.crossasyst.onetoone.model.PersonResponse;
import com.crossasyst.onetoone.repository.AddressRepository;
import com.crossasyst.onetoone.repository.PersonRepository;
import com.jardemo.JarTrial;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.webjars.NotFoundException;
import java.util.Objects;

@Service
@Log4j2
public class PersonAddressService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private AddressMapper addressMapper;

    public PersonResponse createPerson(Person person) {
       try {
           AddressEntity addressEntity = addressMapper.addressToAddressEntity(person.getAddress());
           PersonEntity personEntity = personMapper.personToEntity(person);
           personEntity.setAddressEntity(addressEntity);
           personRepository.save(personEntity);
           PersonResponse personResponse = new PersonResponse();
           personResponse.setId(personEntity.getId());
           log.info("Person with id " + personEntity.getId() + " created successfully");
           return personResponse;
       }catch (Exception e){
           throw new UnexpectedException("An unexpected error occured");
       }
    }

    public Person getPerson(Long id) {
        return personRepository.findById(id).map(this::getPerson).orElseThrow(()-> new PersonNotFoundException("Person Not fOUND " +id));
    }

    private Person getPerson(PersonEntity personEntity){
        AddressEntity addressEntity= personEntity.getAddressEntity();
        Person person=personMapper.entityToPerson(personEntity);
        Address address =addressMapper.addressEntityToAddress(addressEntity);
        person.setAddress(address);
        return person;
    }

    public void deletePerson(Long id) {
        try {
            if(Objects.nonNull(personRepository.findById(id))){
                personRepository.deleteById(id);}
        }catch (Exception e){
            throw  new NotFoundException("");           }
        log.info("Person id {} ", id + " deleted Successfully");
    }

    public Person updatePerson(Long personId, Person person) {
try {
    PersonEntity personEntity1 = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException("Person with id " + personId + " not found"));
    PersonEntity personEntity = personMapper.personToEntity(person);
    AddressEntity addressEntity = addressMapper.addressToAddressEntity(person.getAddress());
    personEntity.setAddressEntity(addressEntity);
    personEntity.getAddressEntity().setId(personEntity1.getAddressEntity().getId());
    personEntity.setId(personId);
    personRepository.save(personEntity);
    return personMapper.entityToPerson(personEntity);
}
    catch (Exception e){
            throw new UnexpectedException("Bad request made by user");
        }
    }

    /* public Person patchPerson(Long id, Map<String, Object> changes) {
        PersonEntity personEntity = personRepository.findById(id).get();
        changes.forEach((change, value) -> {
            switch (change) {
                case "firstName":                   // ===>> PatchMapping using map<k,v> as parameter
                    personEntity.setFirstName((String) value);
                case "lastName":
                    personEntity.setLastName((String) value);
                case "addressEntity":
                    personEntity.setAddressEntity((AddressEntity) value);
            }
        });
        personRepository.save(personEntity);
        return personMapper.entityToPerson(personEntity);
   } */

    @PatchMapping(path = "persons/{id}")
    public void patchPerson(Long id,PatchRequest patchRequest){
            PersonEntity personEntity=personRepository.findById(id).orElseThrow(()->new PersonNotFoundException("Person with id "+id+" not found"));
            personEntity.setFirstName(patchRequest.getFirstName());
            personEntity.setLastName(patchRequest.getLastName());
            personRepository.save(personEntity);
            /* return personMapper.entityToPerson(personEntity);*/
    }
}
