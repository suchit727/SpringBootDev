package com.crossasyst.com;

import lombok.Data;

@Data
public class Person {
    private String firstName;
    private String lastName;
    private int age;
  public String helloPrint(){
      return "Hello";
  }


}
