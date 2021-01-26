package com.kata.demo.core.api.web.query.resource;

import com.kata.demo.common.exception.BadRequestException;
import com.kata.demo.core.api.web.query.mapper.WebHotelMapper;
import com.kata.demo.core.api.web.query.model.WebHotel;
import com.kata.demo.core.api.web.query.model.request.BookingSearchRequest;
import com.kata.demo.core.domain.port.api.HotelSearchRequester;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/web/query")
public class HotelSearchResource {
    private final HotelSearchRequester hotelSearchRequester;

    public HotelSearchResource(HotelSearchRequester hotelSearchRequester) {
        this.hotelSearchRequester = hotelSearchRequester;
    }

    @GetMapping(value = "/room")
    public ResponseEntity<List<WebHotel>> searchRoom(@RequestParam BookingSearchRequest bookingSearchRequest){
        try {
            return ResponseEntity.ok(
                    WebHotelMapper.mapHotels(hotelSearchRequester.getAvailableRooms(bookingSearchRequest.toDto())));
        }catch (BadRequestException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
