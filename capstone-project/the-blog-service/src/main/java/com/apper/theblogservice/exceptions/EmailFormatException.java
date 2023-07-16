package com.apper.theblogservice.exceptions;

public class EmailFormatException extends RuntimeException {

    public EmailFormatException(String message) {
        super(message);
    }
}
