package com.kata.demo.core.api.web.query.model.request;

import com.kata.demo.common.exception.BadRequestException;
import com.kata.demo.core.api.web.query.model.request.BookingSearchRequest;
import com.kata.demo.core.domain.dto.BookingSearchData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingSearchRequestTest {

    @Test(expected = BadRequestException.class)
    public void mapSearchRequestToDto_ShouldThrowsBadRequest_WhenArrivalDateIsGraterThanDepartureDate() throws BadRequestException{
        // Given
        BookingSearchRequest bookingSearchRequest
                = new BookingSearchRequest(LocalDate.of(2021, Month.JANUARY, 15),
                LocalDate.of(2021, Month.JANUARY, 10), 12D, 15D);

        // When
        bookingSearchRequest.toDto();

        Assert.fail();
    }

    @Test(expected = BadRequestException.class)
    public void mapSearchRequestToDto_ShouldThrowsBadRequest_WhenLatitudeIsEmpty() throws BadRequestException{
        // Given
        BookingSearchRequest bookingSearchRequest
                = new BookingSearchRequest(LocalDate.of(2021, Month.JANUARY, 10),
                LocalDate.of(2021, Month.JANUARY, 15), 0D, 15D);

        // When
        bookingSearchRequest.toDto();

        Assert.fail();
    }

    @Test(expected = BadRequestException.class)
    public void mapSearchRequestToDto_ShouldThrowsBadRequest_WhenLongitudeIsEmpty() throws BadRequestException{
        // Given
        BookingSearchRequest bookingSearchRequest
                = new BookingSearchRequest(LocalDate.of(2021, Month.JANUARY, 10),
                LocalDate.of(2021, Month.JANUARY, 15), 12D, 0D);

        // When
        bookingSearchRequest.toDto();

        Assert.fail();
    }

    @Test(expected = BadRequestException.class)
    public void mapSearchRequestToDto_ShouldThrowsBadRequest_WhenArrivalDateIsNull() throws BadRequestException{
        // Given
        BookingSearchRequest bookingSearchRequest
                = new BookingSearchRequest(null,
                LocalDate.of(2021, Month.JANUARY, 15), 0D, 0D);

        // When
        bookingSearchRequest.toDto();

        Assert.fail();
    }

    @Test(expected = BadRequestException.class)
    public void mapSearchRequestToDto_ShouldThrowsBadRequest_WhenDepartureDateIsNull() throws BadRequestException{
        // Given
        BookingSearchRequest bookingSearchRequest
                = new BookingSearchRequest(null,
                LocalDate.of(2021, Month.JANUARY, 15), 0D, 0D);

        // When
        bookingSearchRequest.toDto();

        Assert.fail();
    }

    @Test
    public void mapSearchRequestToDto_ShouldSucceed_WhenValidRequest() throws BadRequestException{
        // Given
        BookingSearchRequest bookingSearchRequest
                = new BookingSearchRequest(LocalDate.of(2021, Month.JANUARY, 10),
                LocalDate.of(2021, Month.JANUARY, 15), 12D, 15D);

        // When
        BookingSearchData bookingSearchData = bookingSearchRequest.toDto();

        // Then
        assertNotNull(bookingSearchData);
        assertEquals(bookingSearchData.getLocation().getLatitude(), 12D, 0);
        assertEquals(bookingSearchData.getLocation().getLongitude(), 15D, 0);
        assert(bookingSearchData.getArrivalDate()).equals(LocalDate.of(2021, Month.JANUARY, 10));
        assert(bookingSearchData.getDepartureDate()).equals(LocalDate.of(2021, Month.JANUARY, 15));
    }
}
