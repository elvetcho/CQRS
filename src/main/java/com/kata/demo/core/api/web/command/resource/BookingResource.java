package com.kata.demo.core.api.web.command.resource;

import com.kata.demo.common.exception.BadRequestException;
import com.kata.demo.core.api.web.command.model.request.BookingRequest;
import com.kata.demo.core.domain.port.api.BookingCommander;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/web/hotel/book")
public class BookingResource {
    private final BookingCommander bookingCommander;

    public BookingResource(BookingCommander bookingCommander) {
        this.bookingCommander = bookingCommander;
    }

    @PostMapping
    public ResponseEntity book(@RequestBody BookingRequest bookingRequest){
        try {
            bookingCommander.book(bookingRequest.toModel());
            return ResponseEntity.ok().build();
        }catch (BadRequestException exception){
            return ResponseEntity.badRequest().build();
        }
    }
}
