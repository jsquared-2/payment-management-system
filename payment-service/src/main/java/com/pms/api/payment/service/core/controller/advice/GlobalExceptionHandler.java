package com.pms.api.payment.service.core.controller.advice;

import com.pms.api.common.lib.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles exceptions on all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles when a client requests an invalid resource.
     *
     * @return a {@link ResponseEntity} with a 400 status code and an error message
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFoundException() {
        // Creating map to hold error to return
        Map<String, String> error = generateGenericMessageError("Invalid resource");

        // Returning 400 response with error message
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Handles when a client requests an invalid HTTP method.
     *
     * @return a {@link ResponseEntity} with a 400 status code and an error message
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleHttpRequestMethodNotSupportedException() {
        // Creating map to hold error to return
        Map<String, String> error = generateGenericMessageError("Invalid HTTP method");

        // Returning 400 response with error message
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Handles JPA validation exceptions.
     *
     * @param exception
     *         the {@link MethodArgumentNotValidException}
     *
     * @return a {@link ResponseEntity} with a 400 status code and an error message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        // Creating map to hold errors to return
        Map<String, String> errors = new HashMap<>();

        // Getting field errors from the exception
        exception.getBindingResult().getFieldErrors()
                 .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        // Return 400 response with error fields
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles when the entity passed does not exist within the data source.
     *
     * @param exception
     *         the {@link com.pms.api.common.lib.exceptions.EntityNotFoundException}
     *
     * @return a {@link ResponseEntity} with a 400 status code indicating a non-existent entity
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException exception) {
        // Creating map to hold error to return
        Map<String, String> error = this.generateGenericFieldError("id", exception);

        // Returning 400 response with error message
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Handles all uncaught exceptions.
     *
     * @return a {@link ResponseEntity} with a 500 status code and an error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException() {
        // Creating map to hold error to return
        Map<String, String> error = generateGenericMessageError();

        // Returning 500 response with error message
        return ResponseEntity.internalServerError().body(error);
    }

    /**
     * Generates a map with a generic error message.
     *
     * @param message
     *         the message associated with the error
     *
     * @return a key-value pair of message and error message content
     */
    private Map<String, String> generateGenericMessageError(String message) {
        // Creating map to hold error to return
        Map<String, String> error = new HashMap<>();

        // Adding error message
        error.put("message", message);

        // Returning error map
        return error;
    }

    /**
     * Generates a map with a generic error message related about an internal server error.
     *
     * @return a key-value pair of message and error message content
     */
    private Map<String, String> generateGenericMessageError() {
        // Creating map to hold error to return
        Map<String, String> error = new HashMap<>();

        // Adding error message
        error.put("message", "Internal server error");

        // Returning error map
        return error;
    }

    /**
     * Generates a map with an error message related to a {@link RuntimeException} associated with a specific field.
     *
     * @param field
     *         the name of the field causing the error
     * @param exception
     *         the {@code RuntimeException} associated with the error
     *
     * @return a key-value pair of field and error message content
     */
    private Map<String, String> generateGenericFieldError(String field, RuntimeException exception) {
        // Creating map to hold error to return
        Map<String, String> error = new HashMap<>();

        // Adding error message
        error.put(field, exception.getMessage());

        // Returning error map
        return error;
    }
}

