package org.example.student;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentService {
    public static void main(String[] args) {
      List<Student> studentList= Arrays.asList(new Student(12L,"suchit","khadtar","pass"),
                new Student(13L,"xyz","abc","failed"),
                new Student(14L,"kunal","dinkar","pass"),
                new Student(15L,"prem ","patil","pass"),
                new Student(16L,"abc","xyz","pass"));
/*
          Stream<Student> list=studentList.stream().filter(student -> student.getResult().equalsIgnoreCase("Pass"));
        Optional<List<Student>> optionalStudents=Optional.of(list.collect(Collectors.toList()));
        optionalStudents.stream().forEach(students -> System.out.println(students));*/
        Stream<Student> list=studentList.stream().filter(student -> student.getResult().equalsIgnoreCase("pass"));
        Optional<List<Student>> passingStudentList=Optional.of(list.collect(Collectors.toList()));
        passingStudentList.stream().forEach(students -> System.out.println(students));
    }
}
