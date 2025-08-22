package com.reactive.design.patterns.orchestrator_parallel.service;

import com.reactive.design.patterns.orchestrator_parallel.dto.OrchestrationRequestContext;
import com.reactive.design.patterns.orchestrator_parallel.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFulfillmentService {

    @Autowired
    private List<Orchestrator> orchestrators;

    public Mono<OrchestrationRequestContext> placeOrder(OrchestrationRequestContext orchestrationRequestContext){
        var list = orchestrators.stream()
                .map(order -> order.create(orchestrationRequestContext))
                .collect(Collectors.toList());

        return Mono.zip(list, array -> array[0])
                .cast(OrchestrationRequestContext.class)
                .doOnNext(this::updateStatus);
    }

    private void updateStatus(OrchestrationRequestContext orchestrationRequestContext){
        var allSuccess = this.orchestrators.stream()
                .allMatch(orderStatus -> orderStatus.isSuccess().test(orchestrationRequestContext));
        var status = allSuccess ? Status.SUCCESS : Status.FAILED;

        orchestrationRequestContext.setStatus(status);
    }

}