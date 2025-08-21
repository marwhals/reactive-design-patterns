package com.reactive.design.patterns.gateway_aggregator.client;


import com.reactive.design.patterns.gateway_aggregator.dto.PromotionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PromotionClient {

    private final PromotionResponse noPromotion = PromotionResponse.create(-1, "no promotion", 0.0, LocalDate.now());
    private final WebClient client;

    public PromotionClient(@Value("${aggregator.promotion.service}") String baseUrl){
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<PromotionResponse> getPromotion(Integer id){
        return this.client
                .get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(PromotionResponse.class)
                .onErrorReturn(noPromotion); //error handling
    }


}
