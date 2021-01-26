package com.kata.demo.core.api.web.query.model.request;

import com.kata.demo.common.exception.BadRequestException;
import com.kata.demo.core.domain.dto.BookingSearchData;
import com.kata.demo.core.domain.model.Coordinates;

import java.time.LocalDate;

public class BookingSearchRequest {
    private LocalDate arrival;
    private LocalDate departure;
    private double latitude;
    private double longitude;

    public BookingSearchRequest(LocalDate arrival, LocalDate departure, double latitude, double longitude) {
        this.arrival = arrival;
        this.departure = departure;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BookingSearchData toDto() throws BadRequestException{
        if (arrival == null || departure == null){
            throw new BadRequestException();
        }
        if(arrival.isAfter(departure) || arrival.isEqual(departure)){
            throw new BadRequestException();
        }
        if(latitude == 0D || longitude == 0D){
            throw new BadRequestException();
        }
        return new BookingSearchData(arrival, departure, new Coordinates(latitude, longitude));
    }
}
