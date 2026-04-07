package com.petproject.incedents.incidents.dto;

import com.petproject.incedents.incidents.IncidentType;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record IncidentResponse (
        Coordinate coordinate,
        String message,
        IncidentType incidentType,
        Boolean isCreated,
        LocalDateTime createdAt) {
}
