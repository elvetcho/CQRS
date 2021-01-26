package com.kata.demo.core.infrastructure.referential.model;

import com.kata.demo.core.domain.model.Room;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Room")
public class MSRoom {
    @Id
    private String id;
    private String name;
    @OneToMany
    private List<MSBooking> bookings;
    private Long version;

    public MSRoom() {
    }

    public MSRoom(Long version) {
        this.version = version;
    }

    public MSRoom(String id, String name, List<MSBooking> bookings, Long version) {
        this.id = id;
        this.name = name;
        this.bookings = bookings;
        this.version = version;
    }

    public List<MSBooking> getBookings() {
        return bookings;
    }

    public Long getVersion() {
        return version;
    }

    public Room toModel() {
        return null;
    }

    public void updateVersion() {
        this.version = this.version + 1;
    }
}
