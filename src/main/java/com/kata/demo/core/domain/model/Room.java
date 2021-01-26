package com.kata.demo.core.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Room {
    private String id;
    private String name;
    private List<Booking> bookings;
    private Long version;

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public Room(String id, String name, List<Booking> bookings, Long version) {
        this.id = id;
        this.name = name;
        this.bookings = bookings;
        this.version = version;
    }

    public Room(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public boolean isRoomAvailable(LocalDate arrivalDate, LocalDate departureDate){
        return bookings.stream().noneMatch(booking -> booking.conflictWith(arrivalDate, departureDate));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Long getVersion() {
        return version;
    }
}
