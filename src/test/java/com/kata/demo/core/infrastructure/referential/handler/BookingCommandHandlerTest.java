package com.kata.demo.core.infrastructure.referential.handler;

import com.kata.demo.common.exception.RoomNotFoundException;
import com.kata.demo.common.exception.VersionMismatchException;
import com.kata.demo.core.domain.coreapi.command.BookHotelRoomCommand;
import com.kata.demo.core.domain.coreapi.event.HotelRoomBookedEvent;
import com.kata.demo.core.domain.coreapi.event.RoomNotFoundEvent;
import com.kata.demo.core.domain.coreapi.event.RoomVersionMismatchEvent;
import com.kata.demo.core.domain.dto.BookingData;
import com.kata.demo.core.domain.model.Booking;
import com.kata.demo.core.domain.model.Room;
import com.kata.demo.core.domain.port.infrastructure.EventGateway;
import com.kata.demo.core.infrastructure.referential.adapter.RoomAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingCommandHandlerTest {

    @Mock
    private RoomAdapter roomAdapter;

    @Mock
    private EventGateway eventGateway;

    @InjectMocks
    private BookingCommandHandler bookingCommandHandler;

    @Test
    public void shouldSendRoomNotFoundEvent_WhenRoomNotFound()
            throws RoomNotFoundException, VersionMismatchException {
        // Given
        BookingData bookingData = new BookingData();
        Booking booking = new Booking(bookingData);
        BookHotelRoomCommand command = new BookHotelRoomCommand(booking);
        doThrow(RoomNotFoundException.class).when(roomAdapter).addBooking(command.booking);
        doNothing().when(eventGateway).publish(any(RoomNotFoundEvent.class));

        // When
        bookingCommandHandler.handle(command);

        // Then
        verify(eventGateway, times(1)).publish(any(RoomNotFoundEvent.class));
    }

    @Test
    public void shouldSendRoomVersionMismatchEvent_WhenRoomVersionIsDifferentThanTargetVersion()
            throws RoomNotFoundException, VersionMismatchException {
        // Given
        BookingData bookingData = new BookingData();
        Booking booking = new Booking(bookingData);
        BookHotelRoomCommand command = BookHotelRoomCommand.create(bookingData);
        doThrow(VersionMismatchException.class).when(roomAdapter).addBooking(command.booking);
        doNothing().when(eventGateway).publish(any(RoomVersionMismatchEvent.class));

        // When
        bookingCommandHandler.handle(command);

        // Then
        verify(eventGateway, times(1)).publish(any(RoomVersionMismatchEvent.class));
    }

    @Test
    public void shouldSendRoomBookedEvent_WhenBookingISValid()
            throws RoomNotFoundException, VersionMismatchException {
        // Given
        BookingData bookingData = new BookingData();
        Booking booking = new Booking(bookingData);
        BookHotelRoomCommand command = BookHotelRoomCommand.create(bookingData);
        Room room = new Room();
        when(roomAdapter.addBooking(command.booking)).thenReturn(room);
        doNothing().when(eventGateway).publish(any(HotelRoomBookedEvent.class));

        // When
        bookingCommandHandler.handle(command);

        // Then
        verify(eventGateway, times(1)).publish(any(HotelRoomBookedEvent.class));
    }

}
