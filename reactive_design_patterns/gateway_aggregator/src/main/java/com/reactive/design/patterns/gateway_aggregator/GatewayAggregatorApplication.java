package com.reactive.design.patterns.gateway_aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.reactive.design.patterns.gateway_aggregator")
public class GatewayAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayAggregatorApplication.class, args);
	}

}
