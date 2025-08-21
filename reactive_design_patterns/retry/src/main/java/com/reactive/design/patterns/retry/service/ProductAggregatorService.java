package com.reactive.design.patterns.retry.service;

import com.reactive.design.patterns.retry.client.ProductClient;
import com.reactive.design.patterns.retry.client.ReviewClient;
import com.reactive.design.patterns.retry.dto.Product;
import com.reactive.design.patterns.retry.dto.ProductAggregate;
import com.reactive.design.patterns.retry.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.reactive.design.patterns.retry")
public class ProductAggregatorService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ReviewClient reviewClient;

    public Mono<ProductAggregate> aggregate(Integer id){
        return Mono.zip(
                        this.productClient.getProduct(id),
                        this.reviewClient.getReviews(id)
                )
                .map(t -> toDto(t.getT1(), t.getT2()));
    }

    private ProductAggregate toDto(Product product, List<Review> reviews){
        return ProductAggregate.create(
                product.getId(),
                product.getCategory(),
                product.getDescription(),
                reviews
        );
    }

}
