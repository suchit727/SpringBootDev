package com.example.springvalidation.mapper;

import com.example.springvalidation.entity.EmployeeEntity;
import com.example.springvalidation.model.EmployeeModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeEntity modelToEntity(EmployeeModel employeeModel);
    EmployeeModel entityToModel(EmployeeEntity employeeEntity);
}
