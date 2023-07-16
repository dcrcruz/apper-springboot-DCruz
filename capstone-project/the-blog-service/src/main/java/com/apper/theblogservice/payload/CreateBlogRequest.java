//package com.apper.theblogservice.payload;
//
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//public class UserRequest {
//
//    @NotBlank(message = "Name is required")
//    private String name;
//
//    @NotBlank(message = "Email is required")
//    @Email(message = "Invalid email format")
//    private String email;
//
//    @NotBlank(message = "Password is required")
//    @Size(min = 5, message = "Password must be at least 5 characters")
//    private String password;
//
//    // Constructors, getters, and setters
//
//    public UserRequest() {
//    }
//
//    public UserRequest(String name, String email, String password) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
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
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}

// above is my implementation

package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBlogRequest {

    @NotBlank(message = "Blog title is required")
    private String title;

    @NotBlank(message = "Blog content is required")
    private String body;

    @JsonProperty("blogger_id")
    @NotBlank(message = "Blogger Id is required")
    private String bloggerId;
}
