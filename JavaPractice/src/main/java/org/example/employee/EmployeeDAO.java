package org.example.employee;

import org.example.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public List<Employee> getEmployee() {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee(1, "suchit", 18000L));
        employeeList.add(new Employee(2, "rushikesh", 2000000L));
        employeeList.add(new Employee(3, "uttam", 500000L));
        employeeList.add(new Employee(4, "kunal", 200000L));
        return employeeList;

    }
}
