package com.example.onetooneinspring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String addressOne;
    private String addressTwo;
    private String city;
    private String state;
    private String zipCode;
}
