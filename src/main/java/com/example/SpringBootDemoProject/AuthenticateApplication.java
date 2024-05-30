package com.example.SpringBootDemoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//@ComponentScan({"com.example.SpringBootDemoProject.other_package", "com.example.SpringBootDemoProject"})
@SpringBootApplication
public class AuthenticateApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AuthenticateApplication.class, args);
    }

}