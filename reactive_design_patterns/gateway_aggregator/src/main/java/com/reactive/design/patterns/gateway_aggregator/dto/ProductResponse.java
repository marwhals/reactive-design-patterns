package com.reactive.design.patterns.gateway_aggregator.dto;


import lombok.Data;
import lombok.ToString;

/**
 * Taken from Swagger API description for external service
 */

@Data
@ToString
public class ProductResponse {
    /**
     * Based on the JSON for a 200 response
     */
    private Integer id;
    private String category;
    private String description;
    private Integer price;

}
