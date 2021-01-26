package com.kata.demo.core.infrastructure.referential.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Hotel")
public class MSHotel {
    @Id
    private String id;
    private String name;
    @Embedded
    private MSCoordinates location;
    @OneToMany
    private List<MSRoom> rooms;

    public MSHotel() {
    }
}
