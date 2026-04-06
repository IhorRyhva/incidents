package com.petproject.incedents.incedents.dto;

import com.petproject.incedents.incedents.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IncidentRequestStatus(
        @NotNull
        @NotEmpty
        Status status
) {
}
