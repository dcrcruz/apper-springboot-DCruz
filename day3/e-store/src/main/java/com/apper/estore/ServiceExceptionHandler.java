package com.apper.estore;

import com.apper.estore.payload.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceError handleInvalidField(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(objectError -> new ServiceError(objectError.getDefaultMessage()))
                .orElse(new ServiceError("Unknown invalid argument encountered"));
    }

    @ExceptionHandler(InvalidUserAgeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleInvalidUserAge(InvalidUserAgeException ex) {
        return ResponseEntity
        .badRequest().body(ex.getMessage());
    }

}