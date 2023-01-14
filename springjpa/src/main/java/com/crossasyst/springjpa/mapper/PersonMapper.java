package com.crossasyst.springjpa.mapper;

import com.crossasyst.springjpa.entity.PersonEntity;
import com.crossasyst.springjpa.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person entityToPerson(PersonEntity personEntity);

    PersonEntity personToEntity(Person person);
}
