package com.example.kafkaspringproducer.controller;

import com.example.kafkaspringproducer.model.Patient;
import com.example.kafkaspringproducer.model.Student;
import com.example.kafkaspringproducer.service.StudentService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  @Autowired
  private StudentService studentService;

  @PostMapping(path = "/student",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Student> postJsonMessage(@RequestBody Student student){
    Student studentResponse=studentService.sendStudent(student);
   return new ResponseEntity<>(studentResponse, HttpStatus.OK);
  }
  @PostMapping(path = "/patient",consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Patient> createEmployee(@RequestBody Patient patient){
    Patient patientResponse=studentService.createPatient(patient);
    return new ResponseEntity<>(patientResponse,HttpStatus.OK);
  }

}
