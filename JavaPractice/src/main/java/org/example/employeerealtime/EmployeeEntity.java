package org.example.employeerealtime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeEntity {
    public static List<Employee> getEmployees() {
        return Stream.of(new Employee(101, "suchit", 'A', 100000d),
                new Employee(102, "prem", 'A', 80000d),
                new Employee(103, "kunal", 'A', 70000d),
                new Employee(104, "uttam", 'A', 30000d),
                new Employee(105, "vaibhav", 'A', 60000d),
                new Employee(106, "Rakesh", 'A', 50000d),
                new Employee(107, "sanket", 'A', 40000d),
                new Employee(108, "Raj", 'A', 20000d)).collect(Collectors.toList());
    }
}
