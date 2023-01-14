package com.crossasyst.kafkaconsumer.config;

import com.crossasyst.kafkaconsumer.model.Patient;
import com.crossasyst.kafkaconsumer.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

@Log4j2
public class PatientDeserializer implements Deserializer<Patient> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
       //Empty Method
    }

    @Override
    public Patient deserialize(String s, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Patient patient=null;
        try {
            patient=objectMapper.readValue(data,Patient.class);

        } catch (IOException e) {

            log.error("Error",e);
        }
        return patient;
    }


    @Override
    public void close() {
       //Empty Method
    }
}
