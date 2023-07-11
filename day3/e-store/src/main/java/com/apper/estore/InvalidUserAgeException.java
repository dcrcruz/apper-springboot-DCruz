package com.apper.estore;

public class InvalidUserAgeException extends RuntimeException {
    private final String message;

    public InvalidUserAgeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
