package com.reactive.design.patterns.orchestrator_parallel.service;

import com.reactive.design.patterns.orchestrator_parallel.client.InventoryClient;
import com.reactive.design.patterns.orchestrator_parallel.dto.OrchestrationRequestContext;
import com.reactive.design.patterns.orchestrator_parallel.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class InventoryOrchestrator extends Orchestrator{

    @Autowired
    private InventoryClient client;

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext orchestrationRequestContext) {
        return this.client.deduct(orchestrationRequestContext.getInventoryRequest())
                .doOnNext(orchestrationRequestContext::setInventoryResponse)
                .thenReturn(orchestrationRequestContext);
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess() {
        return orchestrationRequestContext
                -> Status.SUCCESS.equals(orchestrationRequestContext.getInventoryResponse().getStatus());
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel() {
        return orchestrationRequestContext -> Mono.just(orchestrationRequestContext)
                .filter(isSuccess())
                .map(OrchestrationRequestContext::getInventoryRequest)
                .flatMap(this.client::restore)
                .subscribe();
    }
}