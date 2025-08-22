package com.reactive.design.patterns.orchestrator_sequential.service;


import com.reactive.design.patterns.orchestrator_sequential.dto.OrchestrationRequestContext;
import com.reactive.design.patterns.orchestrator_sequential.exception.OrderFulfillmentFailure;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class Orchestrator {

    public abstract Mono<OrchestrationRequestContext> create(OrchestrationRequestContext orchestrationRequestContext);
    public abstract Predicate<OrchestrationRequestContext> isSuccess();
    public abstract Consumer<OrchestrationRequestContext> cancel();

    protected BiConsumer<OrchestrationRequestContext, SynchronousSink<OrchestrationRequestContext>> statusHandler(){
        return (orchestrationRequestContext, sink) -> {
            if(isSuccess().test(orchestrationRequestContext)){
                sink.next(orchestrationRequestContext);
            }else{
                sink.error(new OrderFulfillmentFailure());
            }
        };
    }


}