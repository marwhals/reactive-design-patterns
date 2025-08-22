package com.reactive.design.patterns.orchestrator_sequential.service;


import com.reactive.design.patterns.orchestrator_sequential.client.ShippingClient;
import com.reactive.design.patterns.orchestrator_sequential.dto.OrchestrationRequestContext;
import com.reactive.design.patterns.orchestrator_sequential.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class ShippingOrchestrator extends Orchestrator{

    @Autowired
    private ShippingClient client;

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext orchestrationRequestContext) {
        return this.client.schedule(orchestrationRequestContext.getShippingRequest())
                .doOnNext(orchestrationRequestContext::setShippingResponse)
                .thenReturn(orchestrationRequestContext);
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess() {
        return orchestrationRequestContext
                -> Status.SUCCESS.equals(orchestrationRequestContext.getShippingResponse().getStatus());
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel() {
        return orchestrationRequestContext -> Mono.just(orchestrationRequestContext)
                .filter(isSuccess())
                .map(OrchestrationRequestContext::getShippingRequest)
                .flatMap(this.client::cancel)
                .subscribe();
    }
}