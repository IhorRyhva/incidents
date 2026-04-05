package com.petproject.incedents.incedents.dto;

import com.petproject.incedents.incedents.Status;

public record IncidentRequest(
        String coordinate,
        String message,
        Status status) {
}
