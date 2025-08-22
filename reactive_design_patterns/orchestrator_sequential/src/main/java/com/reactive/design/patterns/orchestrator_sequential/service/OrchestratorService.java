package com.reactive.design.patterns.orchestrator_sequential.service;


import com.reactive.design.patterns.orchestrator_sequential.client.ProductClient;
import com.reactive.design.patterns.orchestrator_sequential.dto.OrchestrationRequestContext;
import com.reactive.design.patterns.orchestrator_sequential.dto.OrderRequest;
import com.reactive.design.patterns.orchestrator_sequential.dto.OrderResponse;
import com.reactive.design.patterns.orchestrator_sequential.dto.Status;
import com.reactive.design.patterns.orchestrator_sequential.util.DebugUtil;
import com.reactive.design.patterns.orchestrator_sequential.util.OrchestrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrchestratorService {

    @Autowired
    private OrderFulfillmentService fulfillmentService;

    @Autowired
    private OrderCancellationService cancellationService;

    public Mono<OrderResponse> placeOrder(Mono<OrderRequest> mono){
        return mono
                .map(OrchestrationRequestContext::new)
                .flatMap(fulfillmentService::placeOrder)
                .doOnNext(this::doOrderPostProcessing)
                .doOnNext(DebugUtil::print) // for print debugging only
                .map(this::toOrderResponse);
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