package com.kata.demo.core.infrastructure.mongodb.model;

import com.kata.demo.common.exception.RoomNotFoundException;
import com.kata.demo.core.domain.dto.BookingData;
import com.kata.demo.core.domain.model.Booking;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MGHotelTest {

    @Test(expected = RoomNotFoundException.class)
    public void shouldThrowRoomNotFoundException_WhenRoomNotFound() throws RoomNotFoundException{
        // Given
        String roomId = "101";
        Long actualVersion = 1L;
        Booking booking = new Booking(new BookingData());
        MGRoom room = new MGRoom("102");
        MGHotel hotel = new MGHotel(Collections.singletonList(room));

        // When
        hotel.addBooking(roomId, actualVersion, booking);

        // Then
        Assert.fail();
    }

    @Test
    public void shouldUpdateRoomBookings() throws RoomNotFoundException{
        // Given
        String roomId = "101";
        Long actualVersion = 1L;
        Booking booking = new Booking(new BookingData());
        MGRoom room1 = new MGRoom("101", new ArrayList<>(Arrays.asList(new MGBooking())), 0L);
        MGRoom room2 = new MGRoom("102", new ArrayList<>(Arrays.asList(new MGBooking())), 0L);
        MGHotel hotel = new MGHotel(Arrays.asList(room1, room2));

        // When
        hotel.addBooking(roomId, actualVersion, booking);

        // Then
        assertEquals(hotel.getRooms().size(), 2);
        assertEquals(hotel.getRooms().get(0).getBookings().size(), 2);
        assertEquals(hotel.getRooms().get(1).getBookings().size(), 1);
        assertEquals(hotel.getRooms().get(0).getVersion(), 1L, 0);
        assertEquals(hotel.getRooms().get(1).getVersion(), 0L, 0);
    }
}
