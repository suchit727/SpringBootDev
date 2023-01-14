package com.crossasyst.kafkaconsumer.service;

import com.crossasyst.kafkaconsumer.model.Patient;
import com.crossasyst.kafkaconsumer.model.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConsumerService {
    @Value("${topic.name.consumer}")
    private String topicName;
    @Value("${topic.name.consumer1}")
    private String topicName1;

    /*  @KafkaListener(topics = "${topic.name.consumer}", containerFactory = "KafkaStudent")
      public void studentConsume(ConsumerRecord<String, Student> payload)  {

            log.info(payload.value());
           log.info( payload.value().getStudentId());

           log.info(payload.value().getStudentName());
           log.info("This is my object");
        }*/
    @KafkaListener(topics = "${topic.name.consumer}", containerFactory = "KafkaStudent")
    public void studentConsume(Student student) {
        log.info("This is my object");
        log.info(student.getStudentName());
        log.info(student.toString());
    }


    @KafkaListener(topics = "${topic.name.consumer1}", containerFactory = "KafkaPatient")
    public void employeeConsume(Patient patient) {
        log.info("plain patient:{}",patient.toString());
        log.info(patient.getName());

    }


}
