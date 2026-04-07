package com.petproject.incedents.incidents.dto;

import com.petproject.incedents.incidents.IncidentType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IncidentRequestStatus(
        @NotNull
        @NotEmpty
        IncidentType incidentType
) {
}
