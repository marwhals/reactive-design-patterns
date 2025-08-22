package com.reactive.design.patterns.orchestrator_sequential.service;


import com.reactive.design.patterns.orchestrator_sequential.client.UserClient;
import com.reactive.design.patterns.orchestrator_sequential.dto.OrchestrationRequestContext;
import com.reactive.design.patterns.orchestrator_sequential.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class PaymentOrchestrator extends Orchestrator {

    @Autowired
    private UserClient client;

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext orchestrationRequestContext) {
        return this.client.deduct(orchestrationRequestContext.getPaymentRequest())
                .doOnNext(orchestrationRequestContext::setPaymentResponse)
                .thenReturn(orchestrationRequestContext)
                .handle(this.statusHandler());
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess() {
        return orchestrationRequestContext
                -> Status.SUCCESS.equals(orchestrationRequestContext.getPaymentResponse().getStatus());
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel() {
        return orchestrationRequestContext
                -> Mono.just(orchestrationRequestContext)
                .filter(isSuccess())
                .map(OrchestrationRequestContext::getPaymentRequest)
                .flatMap(this.client::refund)
                .subscribe();
    }

}