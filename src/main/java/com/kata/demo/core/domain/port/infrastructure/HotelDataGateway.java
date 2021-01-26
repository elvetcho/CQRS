package com.kata.demo.core.domain.port.infrastructure;

import com.kata.demo.core.domain.model.Coordinates;
import com.kata.demo.core.domain.model.Hotel;

import java.util.List;

public interface HotelDataGateway {
    List<Hotel> getHotelsWithin(Coordinates location);
}
