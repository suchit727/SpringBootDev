package com.example.kafkaspringproducer.service;

import com.example.kafkaspringproducer.model.Patient;
import com.example.kafkaspringproducer.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    KafkaTemplate<String ,Student> kafkaJsontemplate;
    @Autowired
    KafkaTemplate<String,Patient> patientKafkaTemplate;
     @Value("${topic.name.producer}")
     private String topicName;
    @Value("${topic.name.producer1}")
    private String topicName1;

    public Student sendStudent(Student student) {

        kafkaJsontemplate.send(topicName,student);
        return student;
    }


    public Patient createPatient(Patient patient) {

        patientKafkaTemplate.send(topicName1,patient);
        return patient;
    }
}
