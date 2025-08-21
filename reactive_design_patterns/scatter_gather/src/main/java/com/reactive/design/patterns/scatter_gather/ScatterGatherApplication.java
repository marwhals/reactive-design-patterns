package com.reactive.design.patterns.scatter_gather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.reactive.design.patterns.scatter_gather")
public class ScatterGatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScatterGatherApplication.class, args);
	}

}
