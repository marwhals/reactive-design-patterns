package com.reactive.design.patterns.orchestrator_parallel.service;

import com.reactive.design.patterns.orchestrator_parallel.client.ProductClient;
import com.reactive.design.patterns.orchestrator_parallel.dto.*;
import com.reactive.design.patterns.orchestrator_parallel.util.DebugUtil;
import com.reactive.design.patterns.orchestrator_parallel.util.OrchestrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrchestratorService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderFulfillmentService fulfillmentService;

    @Autowired
    private OrderCancellationService cancellationService;

    public Mono<OrderResponse> placeOrder(Mono<OrderRequest> mono){
        return mono
                .map(OrchestrationRequestContext::new)
                .flatMap(this::getProduct)
                .doOnNext(OrchestrationUtil::buildRequestContext)
                .flatMap(fulfillmentService::placeOrder)
                .doOnNext(this::doOrderPostProcessing)
                .doOnNext(DebugUtil::print) // for print debugging only
                .map(this::toOrderResponse);
    }

    private Mono<OrchestrationRequestContext> getProduct(OrchestrationRequestContext orchestrationRequestContext){
        return this.productClient.getProduct(orchestrationRequestContext.getOrderRequest().getProductId())
                .map(Product::getPrice)
                .doOnNext(orchestrationRequestContext::setProductPrice)
                .map(i -> orchestrationRequestContext);
    }

    private void doOrderPostProcessing(OrchestrationRequestContext orchestrationRequestContext){
        if(Status.FAILED.equals(orchestrationRequestContext.getStatus()))
            this.cancellationService.cancelOrder(orchestrationRequestContext);
    }

    private OrderResponse toOrderResponse(OrchestrationRequestContext orchestrationRequestContext){
        var isSuccess = Status.SUCCESS.equals(orchestrationRequestContext.getStatus());
        var address = isSuccess ? orchestrationRequestContext.getShippingResponse().getAddress() : null;
        var deliveryDate = isSuccess ? orchestrationRequestContext.getShippingResponse().getExpectedDelivery() : null;

        return OrderResponse.create(
                orchestrationRequestContext.getOrderRequest().getUserId(),
                orchestrationRequestContext.getOrderRequest().getProductId(),
                orchestrationRequestContext.getOrderId(),
                orchestrationRequestContext.getStatus(),
                address,
                deliveryDate
        );
    }

}