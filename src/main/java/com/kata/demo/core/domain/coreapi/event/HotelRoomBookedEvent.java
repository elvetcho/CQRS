package com.kata.demo.core.domain.coreapi.event;

import com.kata.demo.core.domain.model.Booking;

public class HotelRoomBookedEvent implements Event {
    public final String hotelId;
    public final String roomId;
    public final Long version;
    public final Booking booking;

    public HotelRoomBookedEvent(String hotelId, String roomId, Long version, Booking booking) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.version = version;
        this.booking = booking;
    }
}
