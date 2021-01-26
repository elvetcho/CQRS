package com.kata.demo.core.domain.service;

import com.kata.demo.core.domain.dto.BookingSearchData;
import com.kata.demo.core.domain.model.Hotel;
import com.kata.demo.core.domain.port.api.HotelSearchRequester;
import com.kata.demo.core.domain.port.infrastructure.HotelDataGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelSearchService implements HotelSearchRequester {
    private final HotelDataGateway hotelDataGateway;

    public HotelSearchService(HotelDataGateway hotelDataGateway) {
        this.hotelDataGateway = hotelDataGateway;
    }

    @Override
    public List<Hotel> getAvailableRooms(BookingSearchData bookingSearchData) {
        return hotelDataGateway.
                getHotelsWithin(bookingSearchData.getLocation())
                .stream()
                .peek(hotel -> hotel.filterAvailableRooms(bookingSearchData.getArrivalDate(),
                        bookingSearchData.getDepartureDate()))
                .filter(hotel -> !hotel.getRooms().isEmpty())
                .collect(Collectors.toList());
    }
}
