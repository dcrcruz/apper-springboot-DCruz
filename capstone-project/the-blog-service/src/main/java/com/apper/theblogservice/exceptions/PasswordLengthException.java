package com.apper.theblogservice.exceptions;

public class PasswordLengthException extends RuntimeException {

    public PasswordLengthException(String message) {
        super(message);
    }
}
