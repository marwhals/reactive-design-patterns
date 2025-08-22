package com.reactive.design.patterns.orchestrator_sequential.service;


import com.reactive.design.patterns.orchestrator_sequential.client.ProductClient;
import com.reactive.design.patterns.orchestrator_sequential.dto.OrchestrationRequestContext;
import com.reactive.design.patterns.orchestrator_sequential.dto.Product;
import com.reactive.design.patterns.orchestrator_sequential.dto.Status;
import com.reactive.design.patterns.orchestrator_sequential.util.OrchestrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFulfillmentService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private PaymentOrchestrator paymentOrchestrator;

    @Autowired
    private InventoryOrchestrator inventoryOrchestrator;

    @Autowired
    private ShippingOrchestrator shippingOrchestrator;


    /**
     * Make all the sequential calls here
     */

    public Mono<OrchestrationRequestContext> placeOrder(OrchestrationRequestContext orchestrationRequestContext){
        return this.getProduct(orchestrationRequestContext)
                .doOnNext(OrchestrationUtil::buildPaymentRequest)
                .flatMap(this.paymentOrchestrator::create)
                .doOnNext(OrchestrationUtil::buildInventoryRequest)
                .flatMap(this.inventoryOrchestrator::create)
                .doOnNext(OrchestrationUtil::buildShippingRequest)
                .flatMap(this.shippingOrchestrator::create)
                .doOnNext(c -> c.setStatus(Status.SUCCESS))
                .doOnError(ex -> orchestrationRequestContext.setStatus(Status.FAILED))
                .onErrorReturn(orchestrationRequestContext);
    }

    private Mono<OrchestrationRequestContext> getProduct(OrchestrationRequestContext orchestrationRequestContext){
        return this.productClient.getProduct(orchestrationRequestContext.getOrderRequest().getProductId())
                .map(Product::getPrice)
                .doOnNext(orchestrationRequestContext::setProductPrice)
//                .thenReturn(orchestrationRequestContext); <-- will lead to 500 from external service instead of 404
                .map(i -> orchestrationRequestContext);
    }
}