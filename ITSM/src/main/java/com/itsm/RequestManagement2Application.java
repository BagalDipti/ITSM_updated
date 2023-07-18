package com.itsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RequestManagement2Application {

	public static void main(String[] args) {
		SpringApplication.run(RequestManagement2Application.class, args);
	}

}
