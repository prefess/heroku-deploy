package com.example.SpringBootDemoProject;

import com.example.SpringBootDemoProject.exception.UserException;
import com.example.SpringBootDemoProject.model.User;
import com.example.SpringBootDemoProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        User user = userService.addUser("Nguyen Anh Thoai", "thoai@gmail.com", "abc1234-");
        assertThat(user).isNotNull();
    }

    @Test
    public void login_when_account_is_pending() {
//        User user = userService.addUser("Nguyen Anh Thoai", "thoai@gmail.com", "abc1234-");
        userService.addUser("Nguyen Anh Thoai", "thoai@gmail.com", "abc1234-");
        assertThatThrownBy(() -> {
            userService.login("thoai@gmail.com", "abc1234-");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("User is not activated");
    }

    @Test
    public void login_when_account_is_not_found() {
//        User user = userService.addUser("Nguyen Anh Thoai", "thoai@gmail.com", "abc1234-");
        userService.addUser("Nguyen Anh Thoai", "thoai@gmail.com", "abc1234-");
        assertThatThrownBy(() -> {
            userService.login("thoai123@gmail.com", "abc1234-");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("User is not found");
    }

    @Test
    public void login_when_password_is_incorrect() {
//        User user = userService.addUser("Nguyen Anh Thoai", "thoai@gmail.com", "abc1234-");
        userService.addUserThenAutoActivate("Nguyen Anh Thoai", "thoai@gmail.com", "abc1234-");
        assertThatThrownBy(() -> {
            userService.login("thoai@gmail.com", "abc1234+");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("Password is incorrect");
    }

    @Test
    public void login_successful() {
        userService.addUserThenAutoActivate("Nguyen Anh Thoai", "thoai@gmail.com", "abc1234-");
        User user = userService.login("thoai@gmail.com", "abc1234-");
        assertThat(user).isNotNull();

    }
}