package com.crossasyst.kafkaconsumer.service;

import com.crossasyst.kafkaconsumer.model.Student;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import com.jayway.jsonpath.JsonPath;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Log4j2
public class ConsumerService {
  @Value("${topic.name.consumer}")
  private String topicName;

  @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
  public void consume(ConsumerRecord<String, Student> payload) {
    log.info("Offset: {}", payload.offset());
     log.info("plain:"+payload.value());

    String data= String.valueOf(payload.value());

    ObjectMapper objectMapper= new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,true);
    try {
      Student student=objectMapper.readValue(data, Student.class);
      log.info( student.getStudentName());
      log.info(student.getStudentId());
    }catch (IOException e){
      e.printStackTrace();
    }
      }
}
