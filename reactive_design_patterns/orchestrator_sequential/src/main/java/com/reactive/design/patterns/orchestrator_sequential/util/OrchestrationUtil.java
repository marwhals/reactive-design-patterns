package com.reactive.design.patterns.orchestrator_sequential.util;


import com.reactive.design.patterns.orchestrator_sequential.dto.InventoryRequest;
import com.reactive.design.patterns.orchestrator_sequential.dto.OrchestrationRequestContext;
import com.reactive.design.patterns.orchestrator_sequential.dto.PaymentRequest;
import com.reactive.design.patterns.orchestrator_sequential.dto.ShippingRequest;

public class OrchestrationUtil {

    public static void buildPaymentRequest(OrchestrationRequestContext orchestrationRequestContext){
        var paymentRequest = PaymentRequest.create(
                orchestrationRequestContext.getOrderRequest().getUserId(),
                orchestrationRequestContext.getProductPrice() * orchestrationRequestContext.getOrderRequest().getQuantity(),
                orchestrationRequestContext.getOrderId()
        );
        orchestrationRequestContext.setPaymentRequest(paymentRequest);
    }

    public static void buildInventoryRequest(OrchestrationRequestContext orchestrationRequestContext){
        var inventoryRequest = InventoryRequest.create(
                orchestrationRequestContext.getPaymentResponse().getPaymentId(),
                orchestrationRequestContext.getOrderRequest().getProductId(),
                orchestrationRequestContext.getOrderRequest().getQuantity()
        );
        orchestrationRequestContext.setInventoryRequest(inventoryRequest);
    }

    public static void buildShippingRequest(OrchestrationRequestContext orchestrationRequestContext){
        var shippingRequest = ShippingRequest.create(
                orchestrationRequestContext.getOrderRequest().getQuantity(),
                orchestrationRequestContext.getOrderRequest().getUserId(),
                orchestrationRequestContext.getInventoryResponse().getInventoryId()
        );
        orchestrationRequestContext.setShippingRequest(shippingRequest);
    }

}
