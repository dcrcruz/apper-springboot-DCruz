package com.apper.estore;

import com.apper.estore.payload.CreateUserRequest;
import com.apper.estore.payload.CreateUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("user")
public class UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        System.out.println(request);

        //Lab Exercise: Check user age > 15
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(request.getBirthDate());

        Period age = Period.between(birthDate, currentDate);

        //output if user age is <15
        if (age.getYears() <15) {
            throw new InvalidUserAgeException("User must be at least 15 years old.");
        }

        return new CreateUserResponse("RANDOM_STRING");
    }

}