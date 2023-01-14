package com.crossasyst.onetoone.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PersonEntity {
    @Id
    @SequenceGenerator(name = "seq_person_id", initialValue = 1, allocationSize = 1) //sequenceName = "seq_person_id",
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person_id")
    private Long id;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;
}
