package com.reactive.design.patterns.orchestrator_parallel.service;

import com.reactive.design.patterns.orchestrator_parallel.dto.OrchestrationRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class OrderCancellationService {

    private Sinks.Many<OrchestrationRequestContext> sink;
    private Flux<OrchestrationRequestContext> flux;

    @Autowired
    private List<Orchestrator> orchestrators;

    @PostConstruct
    public void  init(){
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
        this.flux = this.sink.asFlux().publishOn(Schedulers.boundedElastic());
        orchestrators.forEach(order -> this.flux.subscribe(order.cancel()));
    }

    public void cancelOrder(OrchestrationRequestContext ctx){
        this.sink.tryEmitNext(ctx);
    }

}