package org.example.employee;

import lombok.extern.java.Log;
import org.example.employee.Employee;
import org.example.employee.EmployeeDAO;

import java.util.List;
import java.util.stream.Collectors;

@Log
public class EmployeeService {
    public static List<Employee> getTax(String input) {
        List<Employee> employees = new EmployeeDAO().getEmployee();
        if (input.equals("tax")) {
            employees = employees.stream().filter((t) -> t.getSalary() > 500000L).collect(Collectors.toList());
        } else {
            employees = employees.stream().filter((t) -> t.getSalary() <= 500000L).collect(Collectors.toList());
        }
        return employees;
    }

    public static void main(String[] args) {

        System.out.println(getTax("Tax"));
    }
}
