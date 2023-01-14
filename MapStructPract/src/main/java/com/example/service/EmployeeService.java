package com.example.service;

import com.example.entity.EmployeeEntity;
import com.example.mapper.EmployeeEntityMapper;
import com.example.model.Employee;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<EmployeeEntity> employeeEntities;
    @Autowired
    EmployeeEntityMapper employeeEntityMapper;

    public EmployeeService() {
        employeeEntities = new ArrayList<>();
        employeeEntities.add(new EmployeeEntity(1L, "suchit", "khadtar"));
        employeeEntities.add(new EmployeeEntity(2L, "Kunal", "Dinkar"));
        employeeEntities.add(new EmployeeEntity(3L, "prem", "patil"));
        employeeEntities.add(new EmployeeEntity(4L, "Vaibhav", "Jadhav"));
        employeeEntities.add(new EmployeeEntity(5L, "yash", "goyal"));
        employeeEntities.add(new EmployeeEntity(6L, "vishal", "pandey"));
        employeeEntities.add(new EmployeeEntity(7L, "Rakesh", "chavan"));
        employeeEntities.add(new EmployeeEntity(8L, "Raj", "Bokade"));
        employeeEntities.add(new EmployeeEntity(9L, "sanket", "meshra"));
        employeeEntities.add(new EmployeeEntity(10L, "Vipin", "yadav"));


    }

    public Employee getEmployee(Long employeeId) {
      EmployeeEntityMapper mapper= Mappers.getMapper(EmployeeEntityMapper.class);
      EmployeeEntity employeeEntity=new EmployeeEntity(2L,"suchit","Khadtar");
      Employee employee=mapper.entityToEmp(employeeEntity);
      return employee;
    }

}
