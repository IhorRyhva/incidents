package com.petproject.incedents.incedents.dto;

import com.petproject.incedents.incedents.Status;
import lombok.Builder;

@Builder
public record IncidentResponse (
        String coordinate,
        String message,
        Status status) {
}
