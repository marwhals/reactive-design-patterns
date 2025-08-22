package com.reactive.design.patterns.circuit_breaker.client;


import com.reactive.design.patterns.circuit_breaker.dto.Review;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.CustomLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewClient {


    private final WebClient client;

    public ReviewClient(@Value("${circuit.breaker.review.service}") String baseUrl){
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @CircuitBreaker(name = "review-service", fallbackMethod = "fallBackReview")
    public Mono<List<Review>> getReviews(Integer id){
        return this.client
                .get()
                .uri("{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty()) //<---- status check
                .bodyToFlux(Review.class)
                .collectList()
                .retry(5) //<---- implementation -- see retryWhen
                .timeout(Duration.ofMillis(300));
    }

    public Mono<List<Review>> fallBackReview(Integer id, Throwable ex){
        System.out.println("fallback reviews called : " + ex.getMessage());
        return Mono.just(Collections.emptyList());
    }

}