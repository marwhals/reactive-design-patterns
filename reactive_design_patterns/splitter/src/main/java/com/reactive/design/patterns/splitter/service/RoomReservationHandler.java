package com.reactive.design.patterns.splitter.service;


import com.reactive.design.patterns.splitter.client.RoomClient;
import com.reactive.design.patterns.splitter.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RoomReservationHandler extends ReservationHandler{

    @Autowired
    private RoomClient client;

    @Override
    protected ReservationType getType() {
        return ReservationType.ROOM;
    }

    @Override
    protected Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux) {
        return flux.map(this::toRoomRequest)
                   .transform(this.client::reserve)
                   .map(this::toResponse);
    }

    private RoomReservationRequest toRoomRequest(ReservationItemRequest request){
        return RoomReservationRequest.create(
                request.getCity(),
                request.getFrom(),
                request.getTo(),
                request.getCategory()
        );
    }

    private ReservationItemResponse toResponse(RoomReservationResponse response){
        return ReservationItemResponse.create(
                response.getReservationId(),
                this.getType(),
                response.getCategory(),
                response.getCity(),
                response.getCheckIn(),
                response.getCheckOut(),
                response.getPrice()
        );
    }

}
