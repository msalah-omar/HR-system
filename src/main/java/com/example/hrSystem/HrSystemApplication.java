package com.example.hrSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication
public class HrSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrSystemApplication.class, args);
	}

}
