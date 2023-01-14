package com.example.demospring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data @AllArgsConstructor @ToString
public class EmployeeEntity {
    private Long employeeId;
    private String firstName;



    private String lastName;
    public EmployeeEntity() {
    }

}
