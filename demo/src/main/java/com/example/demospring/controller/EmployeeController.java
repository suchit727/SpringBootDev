package com.example.demospring.controller;

import com.example.demospring.entity.EmployeeEntity;
import com.example.demospring.model.Employee;
import com.example.demospring.model.EmployeeResponse;
import com.example.demospring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);

        return new ResponseEntity<>(employee, HttpStatus.OK);

    }

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeEntity> getEmployeeByName(@RequestParam String firstName, @RequestParam String lastName) {
        EmployeeEntity employeeEntityList = employeeService.getByParam(firstName,lastName);

        return new ResponseEntity<>(employeeEntityList, HttpStatus.OK);

    }



    @PostMapping(path = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> createrEmployee(@RequestBody Employee employee) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employee);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);

    }

    @PutMapping(path = "/employees/{employeeId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        Employee empUpdate = employeeService.updateEmployee(employeeId, employee);
        return new ResponseEntity<>(empUpdate, HttpStatus.OK);
    }

    @DeleteMapping(path = "/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok().build();
    }
}


