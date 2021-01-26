package com.kata.demo.core.api.web.command.resource;

import com.kata.demo.common.exception.BadRequestException;
import com.kata.demo.core.api.web.command.model.request.BookingRequest;
import com.kata.demo.core.domain.dto.BookingData;
import com.kata.demo.core.domain.port.api.BookingCommander;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingResourceTest {

    @InjectMocks
    private BookingResource resource;

    @Mock
    private BookingCommander bookingCommander;

    @Test
    public void shouldReturnBadRequest_WhenInputValidationFail() throws BadRequestException{
        // Given
        BookingRequest bookingRequest = mock(BookingRequest.class);
        BookingData bookingData = new BookingData();
        doThrow(BadRequestException.class).when(bookingRequest).toModel();

        // When
        ResponseEntity response = resource.book(bookingRequest);

        // Then
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        verify(bookingCommander, times(0)).book(bookingData);
    }

    @Test
    public void shouldCallBookingCommanderAndReturnsOkResponseEntity() throws BadRequestException {
        // Given
        BookingRequest bookingRequest = mock(BookingRequest.class);
        BookingData bookingData = new BookingData();
        when(bookingRequest.toModel()).thenReturn(bookingData);
        doNothing().when(bookingCommander).book(bookingData);

        // When
        ResponseEntity response = resource.book(bookingRequest);

        // Then
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(bookingCommander, times(1)).book(bookingData);
    }
}
