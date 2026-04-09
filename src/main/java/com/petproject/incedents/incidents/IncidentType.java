package com.petproject.incedents.incidents;

public enum IncidentType {
    /**TODO add radius*/
    ;
    private final double radius;

    IncidentType(double radius){
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
