package com.apper.theblogservice.service;

import com.apper.theblogservice.exceptions.*;
import com.apper.theblogservice.payload.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceError handleValidationException(MethodArgumentNotValidException ex) {
        // Handle validation errors
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ServiceError("ValidationFailed", errorMessage);
    }

    @ExceptionHandler(EmailFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceError handleEmailFormatException(EmailFormatException ex) {
        // Handle email format validation errors
        return new ServiceError("InvalidEmailFormat", ex.getMessage());
    }

    @ExceptionHandler(PasswordLengthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceError handlePasswordLengthException(PasswordLengthException ex) {
        // Handle password length validation errors
        return new ServiceError("InvalidPasswordLength", ex.getMessage());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceError handleDuplicateEmailException(DuplicateEmailException ex) {
        // Handle duplicate email validation errors
        return new ServiceError("EmailAlreadyRegistered", ex.getMessage());
    }

    // Add additional exception handlers for other custom exceptions if needed
    @ExceptionHandler(BloggerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ServiceError handleBloggerNotFoundException(BloggerNotFoundException ex) {
        return new ServiceError("BloggerNotFound", ex.getMessage());
    }

}
