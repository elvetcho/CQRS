package com.kata.demo.core.infrastructure.referential.adapter;

import com.kata.demo.common.exception.RoomNotFoundException;
import com.kata.demo.common.exception.VersionMismatchException;
import com.kata.demo.core.domain.model.Booking;
import com.kata.demo.core.domain.model.Room;
import com.kata.demo.core.infrastructure.referential.model.MSBooking;
import com.kata.demo.core.infrastructure.referential.repository.BookingRepository;
import com.kata.demo.core.infrastructure.referential.repository.RoomRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class RoomAdapter {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public RoomAdapter(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Room addBooking(Booking booking) throws VersionMismatchException, RoomNotFoundException {
        var msRoom = roomRepository.findById(booking.getRoomId())
                .orElseThrow(RoomNotFoundException::new);
        if (msRoom.getVersion().equals(booking.getTargetVersion())) {
            MSBooking msBooking = bookingRepository.save(MSBooking.fromModel(booking));
            msRoom.getBookings().add(msBooking);
            msRoom.updateVersion();
            return roomRepository.save(msRoom).toModel();
        } else {
            throw new VersionMismatchException();
        }
    }
}
