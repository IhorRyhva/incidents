package com.petproject.incedents.exceptions;

public class NotFoundResourceException extends RuntimeException{
    public NotFoundResourceException(Long id) {
        super("Data with this index: " + id + " doesn't exist");
    }
}
