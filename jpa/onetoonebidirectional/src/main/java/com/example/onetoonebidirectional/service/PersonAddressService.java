package com.example.onetoonebidirectional.service;

import com.example.onetoonebidirectional.entity.AddressEntity;
import com.example.onetoonebidirectional.entity.PersonEntity;
import com.example.onetoonebidirectional.mapper.AddressMapper;
import com.example.onetoonebidirectional.mapper.PersonMapper;
import com.example.onetoonebidirectional.model.Address;
import com.example.onetoonebidirectional.model.Person;
import com.example.onetoonebidirectional.model.PersonResponse;
import com.example.onetoonebidirectional.repository.AddressRepository;
import com.example.onetoonebidirectional.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class PersonAddressService {
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PersonRepository personRepository;
    public PersonResponse createPerson(Person person){
        PersonEntity personEntity = personMapper.personToEntity(person);

        AddressEntity addressEntity = addressMapper.addressToEntity(person.getAddress());
        personEntity.setAddressEntity(addressEntity);
        addressEntity.setPersonEntity(personEntity);
        personRepository.save(personEntity);
        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(personEntity.getId());

        log.info("user name{}{} ", personEntity.getFirstName(), personEntity.getLastName() + " created successful");
        return personResponse;


    }
    public Person getPerson(Long id) {
        Optional<PersonEntity> personEntityOptional = personRepository.findById(id);
        Person person = new Person();
        Address address;
        if (personEntityOptional.isPresent()) {
            AddressEntity addressEntity = personEntityOptional.get().getAddressEntity();
            PersonEntity personEntity = personEntityOptional.get();
            person = personMapper.entityToPerson(personEntity);
            address = addressMapper.addressEntityToAddress(addressEntity);
            person.setAddress(address);


        } else {
            log.info("Person id" + id + "Not found");
        }
        return person;
    }

    public void updatePerson(Long id, Person person) {
        PersonEntity personEntity = personRepository.findById(id).get();
        AddressEntity addressEntity = addressRepository.findById(personEntity.getAddressEntity().getId()).get();

        addressEntity.setAddressOne(person.getAddress().getAddressOne());
        addressEntity.setCity(person.getAddress().getCity());
        addressEntity.setState(person.getAddress().getState());
        addressEntity.setZipcode(person.getAddress().getZipcode());
        addressRepository.save(addressEntity);

        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personRepository.save(personEntity);
    }

    public void deletePerson(Long id) {
        Optional<PersonEntity> personEntityOptional = personRepository.findById(id);
        if (personEntityOptional.isPresent()) {
            personRepository.deleteById(id);
        } else {
            log.info("Person id {} ", id + "not found");
        }
    }

    public Address getAddress(Long id) {
        Optional<AddressEntity> optionalAddressEntity=addressRepository.findById(id);
        Address address=new Address();
        Person person;
        if(optionalAddressEntity.isPresent()){
            address=addressMapper.addressEntityToAddress(optionalAddressEntity.get());
            person=personMapper.entityToPerson(optionalAddressEntity.get().getPersonEntity());
            address.setPerson(person);
        }else {
            log.info("Person id" + id + "Not found");
        }
        return address;
    }
}
