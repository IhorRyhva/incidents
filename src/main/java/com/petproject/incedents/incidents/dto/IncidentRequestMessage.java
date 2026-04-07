package com.petproject.incedents.incidents.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IncidentRequestMessage(
        @NotNull
        @NotEmpty
        String message
) {
}
