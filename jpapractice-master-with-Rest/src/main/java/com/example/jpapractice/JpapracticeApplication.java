package com.example.jpapractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JpapracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpapracticeApplication.class, args);

    }
    @Bean
    public RestTemplate restRestTemplate()
    {
        return  new RestTemplate();
    }

}
