package com.example.SpringBootDemoProject;

import com.example.SpringBootDemoProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartRunner implements ApplicationRunner {

    @Autowired
    UserService userService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.addUserThenAutoActivate("maicotion@gmail.com", "maicotion@gmail.com", "abc123");
        userService.addUser("maicotion@yahoo.com", "maicotion@yahoo.com", "abc123");
    }
}
