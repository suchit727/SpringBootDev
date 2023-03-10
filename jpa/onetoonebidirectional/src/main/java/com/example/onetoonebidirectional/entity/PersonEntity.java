package com.example.onetoonebidirectional.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Person")
public class PersonEntity {
    @Id
    @SequenceGenerator(name = "seq_person_id",initialValue = 1,sequenceName = "seq_person_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_person_id")
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name ="last_name")
    private String lastName;
    @OneToOne(mappedBy="personEntity",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id")
    private AddressEntity addressEntity;
}
