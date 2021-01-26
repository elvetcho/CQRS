package com.kata.demo.core.api.web.query.mapper;

import com.kata.demo.core.api.web.query.model.WebHotel;
import com.kata.demo.core.domain.model.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WebHotelMapper {
    public static List<WebHotel> mapHotels(List<Hotel> hotels){
        if(hotels == null){
            return new ArrayList<>();
        }
        return hotels.stream().filter(Objects::nonNull).map(WebHotel::fromModel).collect(Collectors.toList());
    }
}
