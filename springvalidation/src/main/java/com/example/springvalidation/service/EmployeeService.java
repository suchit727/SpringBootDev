package com.example.springvalidation.service;

import com.example.springvalidation.entity.EmployeeEntity;
import com.example.springvalidation.mapper.EmployeeMapper;
import com.example.springvalidation.model.EmployeeModel;
import com.example.springvalidation.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeModel createEmployee(EmployeeModel employeeModel) {
        EmployeeEntity employeeEntity = employeeMapper.modelToEntity(employeeModel);
        employeeRepository.save(employeeEntity);
        log.info("Created Employee Successfully!!");
        return employeeModel;
    }



}
