package com.example.onetoonebidirectional.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String city;
    private String state;
    private String zipcode;
    @JsonIgnore
    private Person person;
}
