package com.example.springwithresttemplate;

import com.example.springwithresttemplate.client.PersonAddressClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class SpringWithRestTemplateApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringWithRestTemplateApplication.class, args);
    }
    @Bean
    public PersonAddressClient personAddressClient(){
        return new PersonAddressClient();
    }
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
