package com.kata.demo.core.domain.port.api;

import com.kata.demo.core.domain.dto.BookingSearchData;
import com.kata.demo.core.domain.model.Hotel;

import java.util.List;

public interface HotelSearchRequester {
    List<Hotel> getAvailableRooms(BookingSearchData bookingSearchData);
}
