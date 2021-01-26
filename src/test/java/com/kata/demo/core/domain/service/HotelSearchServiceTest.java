package com.kata.demo.core.domain.service;

import com.kata.demo.core.domain.dto.BookingSearchData;
import com.kata.demo.core.domain.model.Coordinates;
import com.kata.demo.core.domain.model.Hotel;
import com.kata.demo.core.domain.model.Room;
import com.kata.demo.core.domain.port.infrastructure.HotelDataGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HotelSearchServiceTest {

    @Mock
    private HotelDataGateway hotelDataGateway;

    @InjectMocks
    private HotelSearchService hotelSearchService;

    @Test
    public void shouldReturnsEmptyHotelList_WhenDataGatewayReturnsEmptyList(){
        // Given
        Coordinates location = new Coordinates(10D, 15D);
        LocalDate requestedArrivalDate = LocalDate.of(2021, Month.JANUARY, 10);
        LocalDate requestedDepartureDate = LocalDate.of(2021, Month.JANUARY, 20);
        when(hotelDataGateway.getHotelsWithin(location)).thenReturn(new ArrayList<>());
        BookingSearchData bookingSearchData
                = new BookingSearchData(requestedArrivalDate, requestedDepartureDate, location);

        // When
        List<Hotel> hotels = hotelSearchService.getAvailableRooms(bookingSearchData);

        // Then
        assertEquals(hotels.size(), 0);
    }

    @Test
    public void
    shouldReturnsOnlyHotelsWithAvailableRooms_WhenDataGatewayReturnsHotelsListIncludingHotelsWithoutAvailableRooms(){
        // Given
        Coordinates location = new Coordinates(10D, 15D);
        LocalDate requestedArrivalDate = LocalDate.of(2021, Month.JANUARY, 10);
        LocalDate requestedDepartureDate = LocalDate.of(2021, Month.JANUARY, 20);
        BookingSearchData bookingSearchData
                = new BookingSearchData(requestedArrivalDate, requestedDepartureDate, location);
        Hotel hotelWithoutAvailableRooms = mock(Hotel.class);
        Hotel hotelWithAvailableRooms = mock(Hotel.class);
        when(hotelWithoutAvailableRooms.getRooms()).thenReturn(new ArrayList<>());
        when(hotelWithAvailableRooms.getRooms()).thenReturn(Collections.singletonList(new Room()));
        doNothing().when(hotelWithoutAvailableRooms).filterAvailableRooms(requestedArrivalDate, requestedDepartureDate);
        doNothing().when(hotelWithAvailableRooms).filterAvailableRooms(requestedArrivalDate, requestedDepartureDate);
        when(hotelDataGateway.getHotelsWithin(location))
                .thenReturn(Arrays.asList(hotelWithoutAvailableRooms, hotelWithAvailableRooms));

        // When
        List<Hotel> hotels = hotelSearchService.getAvailableRooms(bookingSearchData);

        // Then
        assertEquals(hotels.size(), 1);
        assertEquals(hotels.get(0), hotelWithAvailableRooms);
    }
}
