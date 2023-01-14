package com.example.mongopract.controller;

import com.example.mongopract.model.Student;
import com.example.mongopract.model.StudentResponse;
import com.example.mongopract.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponse> createStudent(@RequestBody Student student) {
        StudentResponse studentResponse = studentService.createStudent(student);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping(path = "/students/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
