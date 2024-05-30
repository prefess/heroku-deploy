package com.example.SpringBootDemoProject.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Size(min = 5, max = 30, message = "Name must be between 5 and 30")
    private String name;

    @NotBlank(message = "Email cannot null")
    @Email(message = "Not valid email")
    private String email;

    private MultipartFile photo; //https://stackoverflow.com/questions/51383726/why-are-valid-annotations-ignored-on-multipartfile-objects-in-spring
}
