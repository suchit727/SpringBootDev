package com.crossasyst.onetoone.mapper;


import com.crossasyst.onetoone.entities.AddressEntity;
import com.crossasyst.onetoone.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressEntity addressToAddressEntity(Address address);

    Address addressEntityToAddress(AddressEntity addressEntity);
}
