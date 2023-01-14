package com.example.onetooneinspring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @SequenceGenerator(name = "seq_address_id", initialValue = 1, sequenceName = "seq_address_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_id")
    private Long id;
    @Column(name = "address_one")
    private String addressOne;
    @Column(name = "address_two")
    private String addressTwo;
    private String city;
    private String state;
    @Column(name = "zip_code")
    private String zipCode;
    @OneToOne(mappedBy = "addressEntity")
    private PersonEntity personEntity;
}
