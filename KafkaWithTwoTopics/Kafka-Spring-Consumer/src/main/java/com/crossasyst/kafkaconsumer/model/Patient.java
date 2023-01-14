package com.crossasyst.kafkaconsumer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Patient {
    private Long id;
    private String Name;
    private Long age;
}
