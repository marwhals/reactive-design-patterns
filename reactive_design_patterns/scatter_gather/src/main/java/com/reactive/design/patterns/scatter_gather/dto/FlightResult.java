package com.reactive.design.patterns.scatter_gather.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class FlightResult {

    /**
     * Based of 200 response in API doc
     */

    private String airline;
    private String from;
    private String to;
    private Double price;
    private LocalDate date;

}
