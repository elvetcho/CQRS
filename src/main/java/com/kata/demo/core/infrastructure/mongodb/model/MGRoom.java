package com.kata.demo.core.infrastructure.mongodb.model;


import com.kata.demo.core.domain.model.Room;

import java.util.List;

public class MGRoom {
    private String id;
    private String name;
    private List<MGBooking> bookings;
    private Long version;

    public MGRoom(String id) {
        this.id = id;
    }

    public MGRoom(String id, List<MGBooking> bookings, Long version) {
        this.id = id;
        this.bookings = bookings;
        this.version = version;
    }

    public Room toModel(){
        return new Room(name);
    }

    public String getId() {
        return id;
    }

    public List<MGBooking> getBookings() {
        return bookings;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
