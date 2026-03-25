package com.example.interview.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    // This method runs when TaskNotFoundException
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTaskNotFoundException(TaskNotFoundException ex) {

        // Creating a simple response to send back to the client
        Map<String, String> errorResponse = new HashMap<>();

        // Add the error message from the exception
        errorResponse.put("error", ex.getMessage());

        // Return 404 status with the error message
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // This method runs when validation fails (like @NotBlank, @Pattern, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> validationErrors = new HashMap<>();

        // Loop through all validation errors and store them in the map
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        });

        // Return 400 status with all validation errors
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    // Fail safe
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {

        Map<String, String> errorResponse = new HashMap<>();

        // Generic message
        errorResponse.put("error", "Something went wrong. Please try again.");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}