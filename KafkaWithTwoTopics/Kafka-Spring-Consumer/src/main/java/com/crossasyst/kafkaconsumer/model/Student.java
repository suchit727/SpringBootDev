package com.crossasyst.kafkaconsumer.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private Long studentId;
    private String  studentName;
    private Long age;
    private Boolean isEmailRequired;
    private List<Address>  address;
}