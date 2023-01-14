package com.example.kafkaspringproducer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Patient {
    private Long id;
    private String Name;
    private Long age;
}
