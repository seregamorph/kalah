package com.backbase.kalah.exceptions;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<?> handleGameNotFoundException(GameNotFoundException ex) {
        val errorResponse = new ErrorResponse(NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(IllegalStateException ex) {
        val errorResponse = new ErrorResponse(BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        val errorResponse = new ErrorResponse(BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

}
