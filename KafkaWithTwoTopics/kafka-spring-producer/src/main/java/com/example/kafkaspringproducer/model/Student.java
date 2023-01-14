package com.example.kafkaspringproducer.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Student {
  private Long studentId;
  private String  studentName;
  private Long age;
  private Boolean isEmailRequired;
  private List<Address>  address=new ArrayList<>();
}
