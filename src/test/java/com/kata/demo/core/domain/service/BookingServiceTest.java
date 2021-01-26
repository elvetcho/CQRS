package com.kata.demo.core.domain.service;

import com.kata.demo.core.domain.coreapi.command.BookHotelRoomCommand;
import com.kata.demo.core.domain.dto.BookingData;
import com.kata.demo.core.domain.model.Booking;
import com.kata.demo.core.domain.port.api.BookingCommander;
import com.kata.demo.core.domain.port.infrastructure.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @Mock
    private CommandGateway commandGateway;

    @InjectMocks
    private BookingService bookingCommander;

    @Test
    public void shouldInvokeCommandGatewaySendMethod(){
        // Given
        BookingData bookingData = new BookingData();
        Booking booking = new Booking(bookingData);
        BookHotelRoomCommand command = new BookHotelRoomCommand(booking);
        MockedStatic<BookHotelRoomCommand> commandMockStatic = mockStatic(BookHotelRoomCommand.class);
        commandMockStatic.when(() -> BookHotelRoomCommand.create(bookingData))
                .thenReturn(command);
        doNothing().when(commandGateway).send(command);

        // When
        bookingCommander.book(bookingData);

        // Then
        verify(commandGateway, times(1)).send(command);
    }
}
