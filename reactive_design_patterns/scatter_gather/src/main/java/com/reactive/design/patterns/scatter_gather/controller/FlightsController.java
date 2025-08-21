package com.reactive.design.patterns.scatter_gather.controller;

import com.reactive.design.patterns.scatter_gather.dto.FlightResult;
import com.reactive.design.patterns.scatter_gather.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("scatter-gather")
public class FlightsController {

    @Autowired
    private FlightSearchService service;

    @GetMapping(value = "flights/{from}/{to}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<FlightResult> getFlights(@PathVariable String from, @PathVariable String to){
        return this.service.getFlights(from, to);
    }

}
