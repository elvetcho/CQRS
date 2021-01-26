package com.kata.demo.core.infrastructure.mongodb.model;


import com.kata.demo.core.domain.model.Booking;

import java.time.LocalDate;

public class MGBooking {
    private LocalDate arrivalDate;
    private LocalDate departureDate;

    public MGBooking() {
    }

    public MGBooking(LocalDate arrivalDate, LocalDate departureDate) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public static MGBooking fromModel(Booking booking) {
        return null;
    }
}
