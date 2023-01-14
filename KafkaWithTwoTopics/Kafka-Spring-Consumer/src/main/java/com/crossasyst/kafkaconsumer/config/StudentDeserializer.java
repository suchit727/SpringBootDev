package com.crossasyst.kafkaconsumer.config;

import com.crossasyst.kafkaconsumer.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;
@Log4j2
public class StudentDeserializer implements Deserializer<Student> {




    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
     //Empty
        }

    @Override
    public Student deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Student student=null;
        try {
            student=objectMapper.readValue(data,Student.class);

             } catch (IOException e) {
        log.error("error",e);
        }
        return student;
    }

    @Override
    public void close() {
     //Empty
    }
}
