package com.kata.demo.core.api.web.command.model.request;

import com.kata.demo.common.exception.BadRequestException;
import com.kata.demo.core.domain.dto.BookingData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class BookingRequestTest {

    @Test(expected = BadRequestException.class)
    public void shouldThrowsBadRequestException_WhenRequestContainsEmptyFields() throws BadRequestException{
        // Given
        String hotelId = null;
        String roomId = "101";
        LocalDate arrivalDate = LocalDate.of(2020, Month.JANUARY, 15);
        LocalDate departureDate = LocalDate.of(2020, Month.JANUARY, 20);
        Long targetVersion = null;
        BookingRequest bookingRequest = new BookingRequest(hotelId, roomId,arrivalDate, departureDate, targetVersion);

        // When
        bookingRequest.toModel();

        // Then
        Assert.fail();
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowsBadRequestException_WhenArrivalDateIsGreaterThanDepartureDate()
            throws BadRequestException{
        // Given
        String hotelId = "1";
        String roomId = "101";
        LocalDate arrivalDate = LocalDate.of(2020, Month.JANUARY, 20);
        LocalDate departureDate = LocalDate.of(2020, Month.JANUARY, 15);
        Long targetVersion = 0L;
        BookingRequest bookingRequest = new BookingRequest(hotelId, roomId,arrivalDate, departureDate, targetVersion);

        // When
        bookingRequest.toModel();

        // Then
        Assert.fail();
    }

    @Test
    public void shouldReturnBookingData_WhenInputIsValid() throws BadRequestException{
        // Given
        String hotelId = "1";
        String roomId = "101";
        LocalDate arrivalDate = LocalDate.of(2020, Month.JANUARY, 15);
        LocalDate departureDate = LocalDate.of(2020, Month.JANUARY, 20);
        Long targetVersion = 0L;
        BookingRequest bookingRequest = new BookingRequest(hotelId, roomId,arrivalDate, departureDate, targetVersion);

        // When
        BookingData bookingData = bookingRequest.toModel();

        // Then
        assertNotNull(bookingData);
        assertEquals(bookingData.getHotelId(), hotelId);
        assertEquals(bookingData.getRoomId(), roomId);
        assertEquals(bookingData.getArrivalDate(), arrivalDate);
        assertEquals(bookingData.getDepartureDate(), departureDate);
        assertEquals(bookingData.getTargetVersion(), targetVersion);
    }
}
