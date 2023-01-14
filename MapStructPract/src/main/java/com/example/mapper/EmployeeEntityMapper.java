package com.example.mapper;

import com.example.entity.EmployeeEntity;
import com.example.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeEntityMapper
{
      @Mapping(target="firstName",source = "employeeEntity.firstName")
      @Mapping(target="lastName",source="employeeEntity.lasName")
      Employee entityToEmp(EmployeeEntity employeeEntity);


}
