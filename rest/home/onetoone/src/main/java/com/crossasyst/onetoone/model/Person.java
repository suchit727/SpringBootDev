package com.crossasyst.onetoone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Person {

    private  String firstName;

    /*private Boolean isLastNamePresent;*/

    private  String lastName;

    /*private Boolean isNumberPresent;*/

    /*private Long number;*/

    private Address address;
}
