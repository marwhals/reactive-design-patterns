package com.reactive.design.patterns.orchestrator_sequential.client;


import com.reactive.design.patterns.orchestrator_sequential.dto.PaymentRequest;
import com.reactive.design.patterns.orchestrator_sequential.dto.PaymentResponse;
import com.reactive.design.patterns.orchestrator_sequential.dto.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserClient {

    private static final String DEDUCT = "deduct";
    private static final String REFUND = "refund";
    private final WebClient client;

    public UserClient(@Value("${orchestrator.sequential.user.service}") String baseUrl){
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<PaymentResponse> deduct(PaymentRequest request){
        return this.callUserService(DEDUCT, request);
    }

    public Mono<PaymentResponse> refund(PaymentRequest request){
        return this.callUserService(REFUND, request);
    }

    private Mono<PaymentResponse> callUserService(String endPoint, PaymentRequest request){
        return this.client
                .post()
                .uri(endPoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .onErrorReturn(this.buildErrorResponse(request));
    }

    private PaymentResponse buildErrorResponse(PaymentRequest request){
        return PaymentResponse.create(
                null,
                request.getUserId(),
                null,
                request.getAmount(),
                Status.FAILED
        );
    }

}