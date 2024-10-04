package com.example.demo.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class UserForm {

   @NotBlank(message = "Username is required")
   @Size(min = 3, message = "Min 3 Characters is required")
    private String name;

   @Email(message = "Invalid Email Address")
   @NotBlank(message = "Email required")
    private String email;

   @NotBlank(message = "Password is required")
   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#?!])[A-Za-z\\d#?!]{8,}$", 
                    message = "Password must be at least 8 characters long, with 1 uppercase, 1 lowercase, 1 number, and 1 special character")
   @Size(min = 6, message = "Min 6 Characters is required")
    private String password;

    private String about;
   
    @NotBlank(message = " Phone Number is required ")
   @Size(min = 8, max = 12, message = "Invalid Phone Number")
   @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", 
         message = "Please provide a valid phone number with 10 digits, optionally prefixed with a country code.")
    private String phoneNumber;


}