package com.petproject.incedents.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<String> catchNotFoundResourceException(NotFoundResourceException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message: " + exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> catchMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        HashMap<String, String> body = new HashMap<>();
        exception.getBindingResult()
                .getFieldErrors().forEach(
                        fieldError -> body.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(body);
    }
}
