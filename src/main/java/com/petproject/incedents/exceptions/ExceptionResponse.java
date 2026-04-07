package com.petproject.incedents.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ExceptionResponse {
    private final LocalDateTime timestamp;
    private ArrayList<HttpStatus> status = new ArrayList<>();
    private ArrayList<String> error = new ArrayList<>();
    private ArrayList<String> message = new ArrayList<>();
    private ArrayList<String> placeOfError = new ArrayList<>();

    public ExceptionResponse(LocalDateTime now) {
        this.timestamp = now;
    }

    public void addStatus(HttpStatus status) {
        this.status.add(status);
    }

    public void addError(String error) {
        this.error.add(error);
    }

    public void addMessage(String message) {
        this.message.add(message);
    }

    public void addPlaceOfError(String placeOfError) {
        this.placeOfError.add(placeOfError);
    }
}
