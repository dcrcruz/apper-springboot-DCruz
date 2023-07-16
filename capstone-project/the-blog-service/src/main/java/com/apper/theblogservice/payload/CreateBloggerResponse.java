//package com.apper.theblogservice.payload;
//
//public class aUserResponse {
//
//    private String name;
//    private String email;
//
//    // Constructors, getters, and setters
//
//    public UserResponse() {
//    }
//
//    public UserResponse(String name, String email) {
//        this.name = name;
//        this.email = email;
//    }
//
//    // Getters and setters for the fields
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//}

// above is own implementation, below is coach

package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBloggerResponse {
    private String id;

    @JsonProperty("date_registration")
    private LocalDateTime dateRegistration;
}
