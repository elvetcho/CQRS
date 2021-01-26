package com.kata.demo.core.infrastructure.referential.model;

import com.kata.demo.core.domain.model.Booking;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Booking")
public class MSBooking {
    @Id
    private String id;
    private LocalDate arrivalDate;
    private LocalDate departureDate;

    public MSBooking() {
    }

    public MSBooking(String id) {
        this.id = id;
    }



    public static MSBooking fromModel(Booking booking) {
        return null;
    }
}
