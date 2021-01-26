package com.kata.demo.core.infrastructure.referential.adapter;

import com.kata.demo.common.exception.RoomNotFoundException;
import com.kata.demo.common.exception.VersionMismatchException;
import com.kata.demo.core.domain.dto.BookingData;
import com.kata.demo.core.domain.model.Booking;
import com.kata.demo.core.domain.model.Room;
import com.kata.demo.core.infrastructure.referential.model.MSBooking;
import com.kata.demo.core.infrastructure.referential.model.MSRoom;
import com.kata.demo.core.infrastructure.referential.repository.BookingRepository;
import com.kata.demo.core.infrastructure.referential.repository.RoomRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomAdapterTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private RoomAdapter roomAdapter;

    @Test(expected = RoomNotFoundException.class)
    public void addBookingToRoom_ShouldThrowsRoomNotFoundException_WhenRoomNotFound() throws RoomNotFoundException, VersionMismatchException {
        // Given
        when(roomRepository.findById("101")).thenReturn(Optional.empty());
        Booking booking = new Booking(new BookingData("101", 0L));

        // When
        roomAdapter.addBooking(booking);

        // Then
        Assert.fail();
    }

    @Test(expected = VersionMismatchException.class)
    public void addBookingToRoom_ShouldThrowsVersionMismatchException_WhenRoomNotFound() throws RoomNotFoundException, VersionMismatchException {
        // Given
        Long targetVersion = 1L;
        Long actualVersion = 2L;
        String roomId = "101";
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(new MSRoom(actualVersion)));
        Booking booking = new Booking(new BookingData(roomId, targetVersion));

        // When
        roomAdapter.addBooking(booking);

        // Then
        Assert.fail();
    }

    @Test
    public void addBookingToRoom_ShouldReturnsUpdatedRoomWithBooking_WhenBookingIsValid()
            throws RoomNotFoundException, VersionMismatchException{
        // Given
        Long targetVersion = 1L;
        Long actualVersion = 1L;
        String roomId = "101";
        String roomName = "room101";

        MSRoom room = new MSRoom(roomId, "room101", new ArrayList<>(), actualVersion);
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        Booking booking = new Booking(new BookingData(roomId, targetVersion));
        MSBooking msBookingToSave = new MSBooking();
        MSBooking savedMSBooking = new MSBooking("1");
        MockedStatic<MSBooking> msBookingMockedStatic = mockStatic(MSBooking.class);
        msBookingMockedStatic.when(() -> MSBooking.fromModel(booking)).thenReturn(msBookingToSave);
        when(bookingRepository.save(msBookingToSave)).thenReturn(savedMSBooking);
        MSRoom savedRoom = mock(MSRoom.class);
        when(roomRepository.save(room)).thenReturn(savedRoom);
        Room resultRoom = new Room(roomId, roomName, Collections.singletonList(booking), actualVersion);
        when(savedRoom.toModel()).thenReturn(resultRoom);

        // When
        Room result = roomAdapter.addBooking(booking);

        // Then
        assertEquals(result.getId(), resultRoom.getId());
        assertEquals(result.getName(), resultRoom.getName());
        assertEquals(result.getBookings().size(), resultRoom.getBookings().size());
    }
}
