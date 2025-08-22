package com.reactive.design.patterns.orchestrator_parallel.controller;

import com.reactive.design.patterns.orchestrator_parallel.dto.OrderRequest;
import com.reactive.design.patterns.orchestrator_parallel.dto.OrderResponse;
import com.reactive.design.patterns.orchestrator_parallel.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("parallel-orchestrator")
public class OrderController {

    @Autowired
    private OrchestratorService service;

    @PostMapping("order")
    public Mono<ResponseEntity<OrderResponse>> placeOrder(@RequestBody Mono<OrderRequest> mono){
        return this.service.placeOrder(mono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}