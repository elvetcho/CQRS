package com.kata.demo.core.domain.port.api;

import com.kata.demo.core.domain.dto.BookingData;

public interface BookingCommander {
    void book(BookingData bookingData);
}
