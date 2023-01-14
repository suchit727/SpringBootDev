package com.example.onetooneinspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class  OnetooneinspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnetooneinspringApplication.class, args);
	}

}
