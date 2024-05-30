package com.example.SpringBootDemoProject.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email cannot blank")
    @Email(message = "Invalid email")
    String email;

    @Size(min = 5, max = 20, message = "Password length must between 5 and 20 characters")
    String password;

}
