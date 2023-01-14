package org.example.employeerealtime;

public class EmployeeService {
    public static void main(String[] args) {
        double avergae=EmployeeEntity.getEmployees().stream().filter(employee -> employee.getGrade().equals('A'))
                .map(employee -> employee.getSalary())
                .mapToDouble(i->i)
                .average()
                .getAsDouble();
        System.out.println(avergae);
    }
}
