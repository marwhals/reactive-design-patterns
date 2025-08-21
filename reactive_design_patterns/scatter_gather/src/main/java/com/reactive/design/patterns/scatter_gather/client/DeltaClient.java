package com.reactive.design.patterns.scatter_gather.client;

import com.reactive.design.patterns.scatter_gather.dto.FlightResult;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DeltaClient {

    private final WebClient client;

    public DeltaClient(@Value("${scatter.gather.delta.service}") String baseUrl){
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<FlightResult> getFlights(String from, String to){
        return this.client
                .get()
                .uri("{from}/{to}", from, to)
                .retrieve()
                .bodyToFlux(FlightResult.class)
                .onErrorResume(ex -> Mono.empty());
    }

}
