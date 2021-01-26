package com.kata.demo.core.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HotelTest {

    @Test
    public void shouldReturnOnlyAvailableRooms_WhenCertainRoomsAreNotAvailable(){
        // Given
        LocalDate requestedArrivalDate = LocalDate.of(2021, Month.JANUARY, 10);
        LocalDate requestedDepartureDate = LocalDate.of(2021, Month.JANUARY, 20);
        Room bookedRoom = mock(Room.class);
        Room availableRoom = mock(Room.class);
        when(bookedRoom.isRoomAvailable(requestedArrivalDate, requestedDepartureDate)).thenReturn(false);
        when(availableRoom.isRoomAvailable(requestedArrivalDate, requestedDepartureDate)).thenReturn(true);
        Hotel hotel = new Hotel(Arrays.asList(bookedRoom, availableRoom));

        // When
        hotel.filterAvailableRooms(requestedArrivalDate, requestedDepartureDate);

        // Then
        assertEquals(hotel.getRooms().size() , 1);
        assertEquals(hotel.getRooms().get(0), availableRoom);
    }

}
