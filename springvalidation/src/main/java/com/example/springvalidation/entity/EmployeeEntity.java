package com.example.springvalidation.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @SequenceGenerator(name = "seq_employee_id",initialValue = 1,sequenceName = "seq_employee_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_employee_id")
    private Long id;
    @Column(name= "emp_name")
    private String employeeName;
    private String email;
    @Column(name="mod_num")
    private String modileNumber;
    private String gender;
    private Byte age;
    private String nationality;
}
