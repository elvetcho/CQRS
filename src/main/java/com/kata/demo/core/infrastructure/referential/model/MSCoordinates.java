package com.kata.demo.core.infrastructure.referential.model;

import javax.persistence.Embeddable;

@Embeddable
public class MSCoordinates {
    private double latitude;
    private double longitude;

    public MSCoordinates() {
    }

    public MSCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
