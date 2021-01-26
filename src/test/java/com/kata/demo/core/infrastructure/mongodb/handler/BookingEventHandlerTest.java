package com.kata.demo.core.infrastructure.mongodb.handler;

import com.kata.demo.common.exception.HotelNotFoundException;
import com.kata.demo.common.exception.RoomNotFoundException;
import com.kata.demo.core.domain.coreapi.event.HotelNotFoundEvent;
import com.kata.demo.core.domain.coreapi.event.HotelRoomBookedEvent;
import com.kata.demo.core.domain.coreapi.event.RoomNotFoundEvent;
import com.kata.demo.core.domain.dto.BookingData;
import com.kata.demo.core.domain.model.Booking;
import com.kata.demo.core.domain.port.infrastructure.EventGateway;
import com.kata.demo.core.infrastructure.mongodb.adapter.HotelAdapter;
import com.kata.demo.core.infrastructure.mongodb.projector.BookingEventHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingEventHandlerTest {

    @Mock
    private HotelAdapter hotelAdapter;

    @Mock
    private EventGateway eventGateway;

    @InjectMocks
    private BookingEventHandler bookingEventHandler;

    @Test
    public void shouldSendHotelNotFoundEvent_WhenHotelNotFound()
            throws HotelNotFoundException, RoomNotFoundException {
        // Given
        String hotelId = "1";
        String roomId = "101";
        Long actualVersion = 1L;
        Booking booking = new Booking(new BookingData());
        doThrow(HotelNotFoundException.class).when(hotelAdapter).addBooking(hotelId, booking);
        doNothing().when(eventGateway).publish(any(HotelNotFoundEvent.class));

        // When
        bookingEventHandler.on(new HotelRoomBookedEvent(hotelId, roomId, actualVersion, booking));

        // Then
        verify(eventGateway, times(1)).publish(any(HotelNotFoundEvent.class));
    }

    @Test
    public void shouldSendRoomNotFoundEvent_WhenRoomNotFound()
            throws HotelNotFoundException, RoomNotFoundException {
        // Given
        String hotelId = "1";
        String roomId = "101";
        Long actualVersion = 1L;
        Booking booking = new Booking(new BookingData());
        doThrow(RoomNotFoundException.class).when(hotelAdapter).addBooking(hotelId, booking);
        doNothing().when(eventGateway).publish(any(RoomNotFoundEvent.class));

        // When
        bookingEventHandler.on(new HotelRoomBookedEvent(hotelId, roomId, actualVersion, booking));

        // Then
        verify(eventGateway, times(1)).publish(any(RoomNotFoundEvent.class));
    }
}
