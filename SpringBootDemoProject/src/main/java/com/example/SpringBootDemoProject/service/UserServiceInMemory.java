package com.example.SpringBootDemoProject.service;

import com.example.SpringBootDemoProject.exception.UserException;
import com.example.SpringBootDemoProject.model.State;
import com.example.SpringBootDemoProject.model.User;
import com.example.SpringBootDemoProject.repository.UserRepo;
import com.example.SpringBootDemoProject.security.Hashing;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class UserServiceInMemory implements UserService {

    private UserRepo userRepo;
    private Hashing hashing;

//    //Constructor DI
//    public UserServiceInMemory(UserRepo userRepo, Hashing hashing) {
//        this.userRepo = userRepo;
//        this.hashing = hashing;
//    }

    @Override
    public User login(String email, String password) {
        Optional<User> o_user = userRepo.findByEmail(email);
        //Neu user khong ton tai thi bao loi
        if (!o_user.isPresent()) {
            throw new UserException("User is not found!");
        }

        User user = o_user.get();
        //user muon login phai co trang thai active
        if (user.getState() != State.ACTIVE) {
            throw new UserException("User is not activated");
        }

        //Kiem tra password
        if (hashing.validatePassword(password, user.getHashed_password())) {
            return user;
        } else {
            throw new UserException("Password is incorrect");
        }
    }

    @Override
    public boolean logout(String email) {
        return false;
    }

    @Override
    public User addUser(String fullname, String email, String password) {
        return userRepo.addUser(fullname, email, hashing.hashPassword(password));
    }

    @Override
    public User addUserThenAutoActivate(String fullname, String email, String password) {
        return userRepo.addUser(fullname, email, hashing.hashPassword(password), State.ACTIVE);
    }

    @Override
    public Boolean activateUser(String activation_code) {
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User findById(String id) {
        return null;
    }
}
