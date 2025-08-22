package com.reactive.design.patterns.circuit_breaker.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * For runtime overriding
 */
@Configuration
public class CircuitBreakerConfig {

    @Bean
    public CircuitBreakerConfigCustomizer reviewService(){
        return CircuitBreakerConfigCustomizer.of("review-service", builder ->
                    builder.minimumNumberOfCalls(4));
    }


}
