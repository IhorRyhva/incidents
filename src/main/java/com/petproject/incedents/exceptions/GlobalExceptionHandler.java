package com.petproject.incedents.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<ExceptionResponse> catchNotFoundResourceException(NotFoundResourceException exception) {
        ExceptionResponse body = new ExceptionResponse(LocalDateTime.now());
        body.addError(HttpStatus.NOT_FOUND.getReasonPhrase());
        body.addMessage(exception.getMessage());
        body.addStatus(HttpStatus.NOT_FOUND);
        body.addPlaceOfError("Id");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, String>> catchMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        HashMap<String, String> body = new HashMap<>();
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now());
        exception.getBindingResult()
                .getFieldErrors().forEach(
                        fieldError -> body.put(fieldError.getField(), fieldError.getDefaultMessage()));
        for (String key: body.keySet()) {
            response.addError(HttpStatus.BAD_REQUEST.getReasonPhrase());
            response.addMessage(body.get(key));
            response.addStatus(HttpStatus.BAD_REQUEST);
            response.addPlaceOfError(key);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(InvalidCoordinatesException.class)
    public ResponseEntity<ExceptionResponse> catchInvalidCoordinatesException (InvalidCoordinatesException exception) {
        ExceptionResponse body = new ExceptionResponse(LocalDateTime.now());
        body.addError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.addMessage(exception.getMessage());
        body.addStatus(HttpStatus.BAD_REQUEST);
        body.addPlaceOfError("Longitude and latitude");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
