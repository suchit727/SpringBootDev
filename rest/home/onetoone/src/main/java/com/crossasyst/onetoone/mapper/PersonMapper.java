package com.crossasyst.onetoone.mapper;

import com.crossasyst.onetoone.entities.PersonEntity;
import com.crossasyst.onetoone.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PersonMapper {

    PersonEntity personToEntity(Person person);

    Person entityToPerson(PersonEntity personEntity);
}
