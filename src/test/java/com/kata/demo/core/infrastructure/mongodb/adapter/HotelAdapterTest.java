package com.kata.demo.core.infrastructure.mongodb.adapter;

import com.kata.demo.common.exception.HotelNotFoundException;
import com.kata.demo.common.exception.RoomNotFoundException;
import com.kata.demo.core.domain.dto.BookingData;
import com.kata.demo.core.domain.model.Booking;
import com.kata.demo.core.domain.model.Coordinates;
import com.kata.demo.core.domain.model.Hotel;
import com.kata.demo.core.infrastructure.elasticsearch.repository.ESHotelRepository;
import com.kata.demo.core.infrastructure.mongodb.model.MGHotel;
import com.kata.demo.core.infrastructure.mongodb.repository.MGHotelRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HotelAdapterTest {

    @Mock
    private MGHotelRepository mgHotelRepository;

    @Mock
    private ESHotelRepository esHotelRepository;

    @InjectMocks
    private HotelAdapter hotelAdapter;

    @Test
    public void shouldReturnsMappedHotel(){
        // Given
        Coordinates location = new Coordinates(10D, 15D);
        MGHotel mgHotel = mock(MGHotel.class);
        Hotel hotel = new Hotel();
        when(mgHotel.toModel()).thenReturn(hotel);
        when(esHotelRepository.searchWithin(any())).thenReturn(Collections.singletonList("2"));
        when(mgHotelRepository.findAllById(Collections.singletonList("2"))).thenReturn(Collections.singletonList(mgHotel));

        // When
        List<Hotel> hotels = hotelAdapter.getHotelsWithin(location);

        // Then
        assertEquals(hotels.size(), 1);
        assertEquals(hotels.get(0), hotel);
    }

    @Test(expected = HotelNotFoundException.class)
    public void shouldThrownHotelNotFoundException_WhenHotelNotFound() throws HotelNotFoundException, RoomNotFoundException {
        // Given
        String hotelId = "1";
        Booking booking = new Booking(new BookingData());
        when(mgHotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        // When
        hotelAdapter.addBooking(hotelId, booking);

        // Then
        Assert.fail();
    }

    @Test
    public void shouldUpdateHotelWithNewBooking() throws HotelNotFoundException, RoomNotFoundException{
        // Given
        String hotelId = "1";
        String roomId = "101";
        Long actualVersion = 1L;
        Booking booking = new Booking(new BookingData(roomId,actualVersion));
        MGHotel mgHotel = mock(MGHotel.class);
        when(mgHotelRepository.findById(hotelId)).thenReturn(Optional.of(mgHotel));
        doNothing().when(mgHotel).addBooking(roomId, actualVersion, booking);
        when(mgHotelRepository.save(mgHotel)).thenReturn(mgHotel);

        // When
        hotelAdapter.addBooking(hotelId, booking);

        // Then
        verify(mgHotelRepository, times(1)).findById(hotelId);
        verify(mgHotel, times(1)).addBooking(roomId, actualVersion, booking);
        verify(mgHotelRepository, times(1)).save(mgHotel);
    }
}
