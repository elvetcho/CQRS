package com.kata.demo.core.infrastructure.mongodb.adapter;

import com.kata.demo.common.exception.HotelNotFoundException;
import com.kata.demo.common.exception.RoomNotFoundException;
import com.kata.demo.common.util.GeoLocationUtil;
import com.kata.demo.core.domain.model.Booking;
import com.kata.demo.core.domain.model.Coordinates;
import com.kata.demo.core.domain.model.Hotel;
import com.kata.demo.core.domain.port.infrastructure.HotelDataGateway;
import com.kata.demo.core.infrastructure.elasticsearch.repository.ESHotelRepository;
import com.kata.demo.core.infrastructure.elasticsearch.repository.QueryGenerator;
import com.kata.demo.core.infrastructure.mongodb.model.MGHotel;
import com.kata.demo.core.infrastructure.mongodb.repository.MGHotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelAdapter implements HotelDataGateway {
    private final Double DISTANCE = 10D;
    private final String UNIT = "km";
    private final ESHotelRepository esHotelRepository;
    private final MGHotelRepository mgHotelRepository;

    public HotelAdapter(ESHotelRepository esHotelRepository, MGHotelRepository mgHotelRepository) {
        this.esHotelRepository = esHotelRepository;
        this.mgHotelRepository = mgHotelRepository;
    }

    @Override
    public List<Hotel> getHotelsWithin(Coordinates location) {
        List<String> hotelsWithin
                = esHotelRepository.searchWithin(
                        QueryGenerator.generateHotelSearchQuery(
                                GeoLocationUtil.geoPointFromCoordinates(location), DISTANCE, UNIT));
        return mgHotelRepository.findAllById(hotelsWithin).stream()
                .map(MGHotel::toModel).collect(Collectors.toList());
    }

    public Hotel addBooking(String hotelId, Booking booking) throws HotelNotFoundException, RoomNotFoundException {
        var mgHotel = mgHotelRepository.findById(hotelId)
                .orElseThrow(HotelNotFoundException::new);
        mgHotel.addBooking(booking.getRoomId(), booking.getTargetVersion(), booking);
        return mgHotelRepository.save(mgHotel).toModel();
    }
}
