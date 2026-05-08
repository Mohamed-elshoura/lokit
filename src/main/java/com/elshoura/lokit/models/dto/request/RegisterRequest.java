package com.elshoura.lokit.models.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest (
        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 25 ,message = "first name must be between 2 and 25 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 2, max = 25 ,message = "Last name must be between 2 and 25 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6,max = 50,message = "Password must be between 6 and 100 characters")
        String password,

        @Pattern(
                regexp = "^(010|011|012|015)[0-9]{8}$", message = "The Egyptian mobile number is incorrect")
        String phone

){
}
