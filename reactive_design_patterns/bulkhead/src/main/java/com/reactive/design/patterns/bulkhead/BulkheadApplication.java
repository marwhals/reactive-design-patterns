package com.reactive.design.patterns.bulkhead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.reactive.design.patterns.bulkhead")
public class BulkheadApplication {

	public static void main(String[] args) {
		SpringApplication.run(BulkheadApplication.class, args);
	}

}
