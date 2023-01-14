package com.example.kafkaspringproducer.service;

import com.example.kafkaspringproducer.model.Patient;
import com.example.kafkaspringproducer.model.Student;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    KafkaTemplate<String ,Student> kafkaJsontemplate;
    @Value("${topic.name.producer}")
    private String topicName;


    public Student sendStudent(Student student) {

        kafkaJsontemplate.send(topicName,student);
        return student;
    }


}
