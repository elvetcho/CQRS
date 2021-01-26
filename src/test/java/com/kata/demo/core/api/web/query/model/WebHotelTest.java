package com.kata.demo.core.api.web.query.model;

import com.kata.demo.core.domain.model.Hotel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class WebHotelTest {

    @Test
    public void shouldReturnNull_WhenInputIsNull(){
        //Given
        Hotel hotel = null;

        // When
        WebHotel webHotel = WebHotel.fromModel(hotel);

        // Then
        assertNull(webHotel);
    }

    @Test
    public void shouldMapToWebHotel_WhenInputIsValid(){
        //Given
        Hotel hotel = new Hotel("1", "h");

        // When
        WebHotel webHotel = WebHotel.fromModel(hotel);

        // Then
        assertNotNull(webHotel);
        assert(webHotel.getId()).equals("1");
        assert(webHotel.getName()).equals("h");
    }
}
