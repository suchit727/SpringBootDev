package com.example.manytomany.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "emp")
public class EmployeeEntity {
    @Id
    @SequenceGenerator(name = "seq_emp_id", initialValue = 1, sequenceName = "seq_emp_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_emp_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "emp_dept"
            , joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "dept_id", referencedColumnName = "id"))
    private Set<DepartmentEntity> department;
}
