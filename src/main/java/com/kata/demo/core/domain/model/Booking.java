package com.kata.demo.core.domain.model;

import com.kata.demo.core.domain.dto.BookingData;

import java.time.LocalDate;

public class Booking {
    private String hotelId;
    private String roomId;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Long targetVersion;

    public Booking(LocalDate arrivalDate, LocalDate departureDate) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public Booking(BookingData bookingData) {
        this.hotelId = bookingData.getHotelId();
        this.roomId = bookingData.getRoomId();
        this.arrivalDate = bookingData.getArrivalDate();
        this.departureDate = bookingData.getDepartureDate();
        this.targetVersion = bookingData.getTargetVersion();
    }

    public static Booking create(BookingData bookingData) {
        return new Booking(bookingData);
    }

    public boolean conflictWith(LocalDate arrivalDate, LocalDate departureDate){
        return this.arrivalDate.isBefore(departureDate) && this.departureDate.isAfter(arrivalDate);
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public Long getTargetVersion() {
        return targetVersion;
    }
}
