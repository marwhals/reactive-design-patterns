package com.reactive.design.patterns.splitter.service;


import com.reactive.design.patterns.splitter.dto.ReservationItemRequest;
import com.reactive.design.patterns.splitter.dto.ReservationItemResponse;
import com.reactive.design.patterns.splitter.dto.ReservationType;
import reactor.core.publisher.Flux;

public abstract class ReservationHandler {

    protected abstract ReservationType getType();
    protected abstract Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux);

}
