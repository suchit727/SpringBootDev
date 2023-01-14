package com.example.demospring.service;

import com.example.demospring.entity.EmployeeEntity;
import com.example.demospring.model.Employee;
import com.example.demospring.model.EmployeeResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EmployeeService {
    private List<EmployeeEntity> employeeEntities;
    private EmployeeEntity empEntity;

    public EmployeeService() {
        employeeEntities = new ArrayList<>();
        employeeEntities.add(new EmployeeEntity(1L, "suchit", "khadtar"));
        employeeEntities.add(new EmployeeEntity(2L, "Kunal", "Dinkar"));
        employeeEntities.add(new EmployeeEntity(3L, "prem", "patil"));
        employeeEntities.add(new EmployeeEntity(4L, "Vaibhav", "Jadhav"));
        employeeEntities.add(new EmployeeEntity(5L, "yash", "goyal"));
        employeeEntities.add(new EmployeeEntity(6L, "vishal", "pandey"));
        employeeEntities.add(new EmployeeEntity(7L, "Rakesh", "chavan"));
        employeeEntities.add(new EmployeeEntity(8L, "Raj", "Bokade"));
        employeeEntities.add(new EmployeeEntity(9L, "sanket", "meshra"));
        employeeEntities.add(new EmployeeEntity(10L, "Vipin", "yadav"));
    }

    public Employee getEmployee(Long employeeId) {
        Employee employee = new Employee();
        EmployeeEntity employeeEntity = null;

        for (EmployeeEntity e : employeeEntities) {

            if (e.getEmployeeId() == employeeId) {
                employeeEntity = e;

                employee.setFirstName(employeeEntity.getFirstName());

                employee.setLastName(employeeEntity.getLastName());

                break;

            }
        }

        return employee;
    }

    public EmployeeResponse createEmployee(Employee employee) {
       empEntity=new EmployeeEntity();
       empEntity.setFirstName(employee.getFirstName());
       empEntity.setLastName(employee.getLastName());
        Long id = (long) ((Math.random() * 20));
        EmployeeResponse employeeResponse = new EmployeeResponse();
        empEntity.setEmployeeId(id);
        employeeResponse.setId(empEntity.getEmployeeId());
        employeeEntities.add(empEntity);
        log.info("Employee Name is {} {}", employee.getFirstName(), employee.getLastName());
        return employeeResponse;
    }


    public Employee updateEmployee(Long employeeId, Employee employee) {

        for (EmployeeEntity e :
                employeeEntities) {
            if (employeeId == e.getEmployeeId()) {
                e.setFirstName(employee.getFirstName());
                e.setLastName(employee.getLastName());
                break;
            }

        }
        return employee;
    }


    public void deleteEmployee(Long employeeId) {
        for (EmployeeEntity e :
                employeeEntities) {
            if (e.getEmployeeId() == employeeId) {
                employeeEntities.remove(employeeId);
            }
        }
        log.info("Deleted");

    }

    public Employee getEmployeeSearch() {
        Employee employee = new Employee();
        EmployeeEntity employeeEntity = null;
        for (EmployeeEntity e :
                employeeEntities) {
            employee.setFirstName(employeeEntity.getFirstName());

            employee.setLastName(employeeEntity.getLastName());

        }
        return employee;
    }

    public EmployeeEntity getByParam(String firstName, String lastName) {
        EmployeeEntity eE = null;
        for (EmployeeEntity e :
                employeeEntities) {
            if (e.getFirstName().equalsIgnoreCase(firstName) && e.getLastName().equalsIgnoreCase(lastName)) {
                eE = e;


            }
        }
        return eE;
    }
}
