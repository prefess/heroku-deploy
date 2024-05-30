package com.example.SpringBootDemoProject.repository;

import com.example.SpringBootDemoProject.model.State;
import com.example.SpringBootDemoProject.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public User addUser(String fullName, String email, String hashed_password) {
        return addUser(fullName, email, hashed_password, State.PENDING);
    }

    public User addUser(String fullName, String email, String hashed_password, State state) {
        String id = UUID.randomUUID().toString();
        User user = User.builder()
                .id(id)
                .fullname(fullName)
                .email(email)
                .hashed_password(hashed_password)
                .state(state)
                .build();
        users.put(id, user);
        return user;
    }

    public boolean isEmailExist(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email)).count() > 0;
    }

    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }
}
