package com.petproject.incedents.incidents.dto;

import com.petproject.incedents.incidents.IncidentType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record IncidentRequest(
        @NotNull
        Coordinate coordinate,

        @NotEmpty
        String message,

        @NotNull
        IncidentType incidentType,

        @NotNull
        LocalDate dateOfGenerate
) {
}
