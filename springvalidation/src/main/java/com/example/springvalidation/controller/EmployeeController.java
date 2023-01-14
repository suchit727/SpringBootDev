package com.example.springvalidation.controller;

import com.example.springvalidation.model.EmployeeModel;
import com.example.springvalidation.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@Log4j2
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeModel> createEmployee(@RequestBody @Valid EmployeeModel employeeModel) {
        if (Objects.isNull(employeeModel) != true){
        employeeModel =employeeService.createEmployee(employeeModel);
        log.info("Employee Model is not null!!!");
    }else{
            log.info("Cannot Pass null objects");
        }
        return new ResponseEntity<>(employeeModel, HttpStatus.OK);
    }

}
