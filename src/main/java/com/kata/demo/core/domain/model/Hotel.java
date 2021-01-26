package com.kata.demo.core.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    private String id;
    private String name;
    private Coordinates location;
    private List<Room> rooms;

    public Hotel() {
    }

    public Hotel(String id, String name, Coordinates location, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rooms = rooms;
    }

    public Hotel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hotel(List<Room> rooms) {
        this.rooms = rooms;
    }



    public void filterAvailableRooms(LocalDate arrivalDate, LocalDate departureDate){
        rooms = rooms.stream()
                .filter(room -> room.isRoomAvailable(arrivalDate, departureDate))
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
