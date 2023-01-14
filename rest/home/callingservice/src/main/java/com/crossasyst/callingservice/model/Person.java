package com.crossasyst.callingservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Person {

    private String firstName;
    private String lastName;
    private Address address;
}