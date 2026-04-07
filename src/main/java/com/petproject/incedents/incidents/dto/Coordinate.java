package com.petproject.incedents.incidents.dto;

import com.petproject.incedents.exceptions.InvalidCoordinatesException;
import jakarta.persistence.Embeddable;

@Embeddable
public class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude) {
        this.checkData(latitude, longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Coordinate() {
    }

    private void checkData(double latitude, double longitude) {
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new InvalidCoordinatesException(String.format("Invalid coordinates: latitude-%f, longitude-%f"), latitude, longitude);
        }
    }
}
