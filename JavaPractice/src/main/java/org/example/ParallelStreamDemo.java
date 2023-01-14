package org.example;

import org.example.employeerealtime.EmployeeEntity;

import java.util.stream.IntStream;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        long start = 0;
        long end = 0;


        IntStream.range(1, 100).forEach(t -> System.out.println(t));
        System.out.println("==========================================================================");
        IntStream.range(1, 100).parallel().forEach(t -> System.out.println(t));


        start = System.currentTimeMillis();
        double avergae = EmployeeEntity.getEmployees().stream().filter(employee -> employee.getGrade().equals('A'))
                .map(employee -> employee.getSalary())
                .mapToDouble(i -> i)
                .average()
                .getAsDouble();
        end = System.currentTimeMillis();
        System.out.println("plain Stream: " + avergae);

        System.out.println("Plain stream takes miliseconds: " + (end - start));

        start = System.currentTimeMillis();
        double avergaeParallel = EmployeeEntity.getEmployees().parallelStream().filter(employee -> employee.getGrade().equals('A'))
                .map(employee -> employee.getSalary())
                .mapToDouble(i -> i)
                .average()
                .getAsDouble();
        end = System.currentTimeMillis();
        System.out.println("parallel Stream: " + avergaeParallel);

        System.out.println("Parallel Stream takes miliseconds: " + (end - start));
    }
}
