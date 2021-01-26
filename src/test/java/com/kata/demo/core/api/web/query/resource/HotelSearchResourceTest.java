package com.kata.demo.core.api.web.query.resource;

import com.kata.demo.common.exception.BadRequestException;
import com.kata.demo.core.api.web.query.model.WebHotel;
import com.kata.demo.core.api.web.query.model.request.BookingSearchRequest;
import com.kata.demo.core.api.web.query.resource.HotelSearchResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HotelSearchResourceTest {

    @InjectMocks
    HotelSearchResource hotelSearchResource;

    @Test
    public void shouldReturnsBadRequestResponseCode_WhenInvalidRequest() throws BadRequestException{
        // Given
        BookingSearchRequest bookingSearchRequest = mock(BookingSearchRequest.class);
        doThrow(BadRequestException.class).when(bookingSearchRequest).toDto();

        // When
        ResponseEntity<List<WebHotel>> response = hotelSearchResource.searchRoom(bookingSearchRequest);

        // Then
        assertNotNull(response);
        assert(response.getStatusCode()).equals(HttpStatus.BAD_REQUEST);
    }
}
