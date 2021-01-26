package com.kata.demo.core.domain.dto;

import com.kata.demo.core.domain.model.Coordinates;

import java.time.LocalDate;

public class BookingSearchData {
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Coordinates location;

    public BookingSearchData(LocalDate arrivalDate, LocalDate departureDate, Coordinates location) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.location = location;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public Coordinates getLocation() {
        return location;
    }
}
