package com.reactive.design.patterns.rate_limiter.service;


import com.reactive.design.patterns.rate_limiter.client.ProductClient;
import com.reactive.design.patterns.rate_limiter.client.ReviewClient;
import com.reactive.design.patterns.rate_limiter.dto.Product;
import com.reactive.design.patterns.rate_limiter.dto.ProductAggregate;
import com.reactive.design.patterns.rate_limiter.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
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
