package com.reactive.design.patterns.timeout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.reactive.design.patterns.timeout")
public class TimeoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeoutApplication.class, args);
	}

}
