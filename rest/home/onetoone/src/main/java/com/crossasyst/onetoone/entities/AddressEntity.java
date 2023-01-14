package com.crossasyst.onetoone.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class AddressEntity {
    @Id
    @SequenceGenerator(name = "seq_address_id",sequenceName = "seq_address_id", initialValue = 1,allocationSize = 1) // sequenceName = "seq_address_id",
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_id")
    private Long id;
    private String addressOne;
    private String addressTwo;
    private String city;
    private String state;
    private String zipCode;
    @OneToOne(mappedBy = "addressEntity")
    private PersonEntity personEntity;
}
