package com.example.jpapractice.mapper;

import com.example.jpapractice.entity.PersonEntity;
import com.example.jpapractice.model.Person;
import com.example.jpapractice.model.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel ="spring")
public interface PersonMapper {
    PersonMapper Instance = Mappers.getMapper(PersonMapper.class);


    PersonEntity personToEntity(Person person);
    PersonEntity personResposeToEntity(PersonResponse response);
   PersonResponse EntityTopersonResponse(PersonEntity personEntity);
    Person entityToPerson(PersonEntity personEntity);

}
