package com.kata.demo.core.domain.service;

import com.kata.demo.core.domain.coreapi.command.BookHotelRoomCommand;
import com.kata.demo.core.domain.dto.BookingData;
import com.kata.demo.core.domain.port.api.BookingCommander;
import com.kata.demo.core.domain.port.infrastructure.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements BookingCommander {
    private final CommandGateway commandGateway;

    public BookingService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public void book(BookingData bookingData) {
        commandGateway.send(BookHotelRoomCommand.create(bookingData));
    }
}
