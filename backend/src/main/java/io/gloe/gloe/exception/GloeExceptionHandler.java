package io.gloe.gloe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloeExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<GloeErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(GloeException.class)
    public ResponseEntity<GloeErrorResponse> handleGloeException(GloeException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GloeErrorResponse> handleGenericException(Exception ex) {
        // You can log ex here if needed
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }

    private ResponseEntity<GloeErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        GloeErrorResponse error = new GloeErrorResponse();
        error.setStatus(status.value());
        error.setMessage(message);
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, status);
    }
}
