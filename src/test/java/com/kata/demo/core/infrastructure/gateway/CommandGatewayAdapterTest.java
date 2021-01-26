package com.kata.demo.core.infrastructure.gateway;

import com.kata.demo.core.domain.coreapi.command.BookHotelRoomCommand;
import com.kata.demo.core.domain.dto.BookingData;
import com.kata.demo.core.domain.model.Booking;
import com.kata.demo.core.domain.port.infrastructure.EventGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommandGatewayAdapterTest {

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private CommandGatewayAdapter commandGatewayAdapter;

    @Test
    public void commandGatewayShouldInvokeApplicationEventPublisher_WhenSendingBookHotelRoomCommand(){
        // Given
        BookHotelRoomCommand command = new BookHotelRoomCommand(new Booking(new BookingData()));
        doNothing().when(eventPublisher).publishEvent(command);

        // When
        commandGatewayAdapter.send(command);

        // Then
        verify(eventPublisher, times(1)).publishEvent(command);
    }

}
