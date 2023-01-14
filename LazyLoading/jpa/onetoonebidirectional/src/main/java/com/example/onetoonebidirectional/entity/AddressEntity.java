package com.example.onetoonebidirectional.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="address")
public class AddressEntity {
    @Id
    private Long id;
    @Column(name = "address_one")
    private String addressOne;
    private String city;
    private String state;
    private String zipcode;
    @MapsId
    @OneToOne
    @JoinColumn(name="id")
    private PersonEntity personEntity;

}
