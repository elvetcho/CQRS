package com.kata.demo.core.domain.coreapi.command;

import com.kata.demo.core.domain.dto.BookingData;
import com.kata.demo.core.domain.model.Booking;

public class BookHotelRoomCommand implements Command {

    public final Booking booking;

    public BookHotelRoomCommand(Booking booking) {
        this.booking = booking;
    }

    public static BookHotelRoomCommand create(BookingData bookingData){
        return new BookHotelRoomCommand(Booking.create(bookingData));
    }
}
