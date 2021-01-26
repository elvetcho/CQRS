package com.kata.demo.core.infrastructure.referential.handler;

import com.kata.demo.common.exception.RoomNotFoundException;
import com.kata.demo.common.exception.VersionMismatchException;
import com.kata.demo.core.domain.coreapi.command.BookHotelRoomCommand;
import com.kata.demo.core.domain.coreapi.event.HotelRoomBookedEvent;
import com.kata.demo.core.domain.coreapi.event.RoomNotFoundEvent;
import com.kata.demo.core.domain.coreapi.event.RoomVersionMismatchEvent;
import com.kata.demo.core.domain.model.Room;
import com.kata.demo.core.domain.port.infrastructure.EventGateway;
import com.kata.demo.core.infrastructure.referential.adapter.RoomAdapter;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class BookingCommandHandler {
    private final EventGateway eventGateway;
    private final RoomAdapter roomAdapter;

    public BookingCommandHandler(EventGateway eventGateway, RoomAdapter roomAdapter) {
        this.eventGateway = eventGateway;
        this.roomAdapter = roomAdapter;
    }

    @EventListener
    @Transactional
    public void handle(BookHotelRoomCommand command){
        try {
            Room room = roomAdapter.addBooking(command.booking);
            eventGateway.publish(new HotelRoomBookedEvent(command.booking.getHotelId(),
                    room.getId(), room.getVersion(), command.booking));
        }catch (RoomNotFoundException exception){
            eventGateway.publish(new RoomNotFoundEvent());
        }catch (VersionMismatchException exception){
            eventGateway.publish(new RoomVersionMismatchEvent());
        }
    }
}
