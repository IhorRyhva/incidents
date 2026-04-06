package com.petproject.incedents.incedents.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IncidentRequestMessage(
        @NotNull
        @NotEmpty
        String message
) {
}
