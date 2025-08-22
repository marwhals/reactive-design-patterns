package com.reactive.design.patterns.orchestrator_parallel.util;

import com.reactive.design.patterns.orchestrator_parallel.dto.InventoryRequest;
import com.reactive.design.patterns.orchestrator_parallel.dto.OrchestrationRequestContext;
import com.reactive.design.patterns.orchestrator_parallel.dto.PaymentRequest;
import com.reactive.design.patterns.orchestrator_parallel.dto.ShippingRequest;

public class OrchestrationUtil {

    public static void buildRequestContext(OrchestrationRequestContext orchestrationRequestContext){
        buildPaymentRequest(orchestrationRequestContext);
        buildInventoryRequest(orchestrationRequestContext);
        buildShippingRequest(orchestrationRequestContext);
    }

    private static void buildPaymentRequest(OrchestrationRequestContext orchestrationRequestContext){
        var paymentRequest = PaymentRequest.create(
                orchestrationRequestContext.getOrderRequest().getUserId(),
                orchestrationRequestContext.getProductPrice() * orchestrationRequestContext.getOrderRequest().getQuantity(),
                orchestrationRequestContext.getOrderId()
        );
        orchestrationRequestContext.setPaymentRequest(paymentRequest);
    }

    private static void buildInventoryRequest(OrchestrationRequestContext orchestrationRequestContext){
        var inventoryRequest = InventoryRequest.create(
                orchestrationRequestContext.getOrderId(),
                orchestrationRequestContext.getOrderRequest().getProductId(),
                orchestrationRequestContext.getOrderRequest().getQuantity()
        );
        orchestrationRequestContext.setInventoryRequest(inventoryRequest);
    }

    private static void buildShippingRequest(OrchestrationRequestContext orchestrationRequestContext){
        var shippingRequest = ShippingRequest.create(
                orchestrationRequestContext.getOrderRequest().getQuantity(),
                orchestrationRequestContext.getOrderRequest().getUserId(),
                orchestrationRequestContext.getOrderId()
        );
        orchestrationRequestContext.setShippingRequest(shippingRequest);
    }

}
