package com.example.onetooneinspring.mapper;

import com.example.onetooneinspring.entity.AddressEntity;
import com.example.onetooneinspring.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntity addressToAddressEntity(Address address);
    Address addressEntityToAddress(AddressEntity addressEntity);
}
