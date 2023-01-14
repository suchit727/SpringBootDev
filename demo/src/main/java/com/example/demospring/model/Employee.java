package com.example.demospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Employee {

    private String firstName;
    private String lastName;

    public Employee() {
    }

}
