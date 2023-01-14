package com.example.mongopract.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends StudentResponse {
    private String name;
    private String city;
    private Integer age;
}
