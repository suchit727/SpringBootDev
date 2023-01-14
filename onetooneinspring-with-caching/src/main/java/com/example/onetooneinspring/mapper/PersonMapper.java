package com.example.onetooneinspring.mapper;

import com.example.onetooneinspring.entity.AddressEntity;
import com.example.onetooneinspring.entity.PersonEntity;
import com.example.onetooneinspring.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel="spring")
public interface PersonMapper {
    PersonEntity personToEntity(Person person);
    Person entityToPerson(PersonEntity personEntity);


}
