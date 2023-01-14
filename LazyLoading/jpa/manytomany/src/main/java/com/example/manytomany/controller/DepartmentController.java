package com.example.manytomany.controller;

import com.example.manytomany.model.Department;
import com.example.manytomany.model.DepartmentResponse;
import com.example.manytomany.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "/departments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentResponse> createEmployee(@RequestBody Department department) {
        DepartmentResponse departmentResponse = departmentService.createEmployee(department);
        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }

}
