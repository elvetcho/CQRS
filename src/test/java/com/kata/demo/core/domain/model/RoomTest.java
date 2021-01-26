package com.kata.demo.core.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomTest {

    @Test
    public void shouldReturnsFalse_WhenRoomHaveConflictingBooking(){
        // Given
        LocalDate requestedArrivalDate = LocalDate.of(2021, Month.JANUARY, 10);
        LocalDate requestedDepartureDate = LocalDate.of(2021, Month.JANUARY, 20);
        Booking booking = mock(Booking.class);
        when(booking.conflictWith(requestedArrivalDate, requestedDepartureDate)).thenReturn(true);
        Room room = new Room(Collections.singletonList(booking));

        // When
        boolean isAvailable = room.isRoomAvailable(requestedArrivalDate, requestedDepartureDate);

        // Then
        assertFalse(isAvailable);
    }

    @Test
    public void shouldReturnsTrue_WhenRoomDoesNotHaveConflictingBooking(){
        // Given
        LocalDate requestedArrivalDate = LocalDate.of(2021, Month.JANUARY, 10);
        LocalDate requestedDepartureDate = LocalDate.of(2021, Month.JANUARY, 20);
        Booking booking = mock(Booking.class);
        when(booking.conflictWith(requestedArrivalDate, requestedDepartureDate)).thenReturn(false);
        Room room = new Room(Collections.singletonList(booking));

        // When
        boolean isAvailable = room.isRoomAvailable(requestedArrivalDate, requestedDepartureDate);

        // Then
        assertTrue(isAvailable);
    }

    @Test
    public void shouldReturnsTrue_WhenRoomBookingsIsEmpty(){
        // Given
        LocalDate requestedArrivalDate = LocalDate.of(2021, Month.JANUARY, 10);
        LocalDate requestedDepartureDate = LocalDate.of(2021, Month.JANUARY, 20);
        Room room = new Room(new ArrayList<>());

        // When
        boolean isAvailable = room.isRoomAvailable(requestedArrivalDate, requestedDepartureDate);

        // Then
        assertTrue(isAvailable);
    }
}
