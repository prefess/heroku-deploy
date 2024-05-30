package com.example.SpringBootDemoProject;

import com.example.SpringBootDemoProject.model.State;
import com.example.SpringBootDemoProject.model.User;
import com.example.SpringBootDemoProject.repository.UserRepo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserRepo {
    @Test
    public void addUser() {
        UserRepo userRepo = new UserRepo();

        User user = userRepo.addUser("John Levy", "levy@gmail.com", "0X-1321am21321", State.PENDING);
        assertThat(user).isNotNull();
        System.out.println(user.getId());
        assertThat(user.getId()).isNotBlank();
    }

    @Test
    public void addUserWithoutState() {
        UserRepo userRepo = new UserRepo();

        User user = userRepo.addUser("John Levy", "levy@gmail.com", "0X-1321am21321");
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getState()).isEqualTo(State.PENDING);
    }

    @Test
    public void isEmailExist() {
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("John Levy", "johnlevy@gmail.com", "0X-1321am21321");
        userRepo.addUser("John", "john@gmail.com", "0X-1321232546");
        userRepo.addUser("Levy", "levy@gmail.com", "0X-1321657721");

        assertThat(userRepo.isEmailExist("johnlevy@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("levY@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("john@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("levi@gmail.com")).isFalse();
    }

    @Test
    public void findByEmail() {
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("John Levy", "johnlevy@gmail.com", "0X-1321am21321");
        userRepo.addUser("John", "john@gmail.com", "0X-1321232546");
        userRepo.addUser("Levy", "levy@gmail.com", "0X-1321657721");

        assertThat(userRepo.findByEmail("levy@GMAIL.com")).isPresent();
        assertThat(userRepo.findByEmail("JOHN@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("levyjohn@gmail.com")).isNotPresent();
    }
}
