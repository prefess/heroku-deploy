package com.example.SpringBootDemoProject.security;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHAHash implements Hashing {

    //chu y day la mot lo hong baor mat, dung ra can phai sinh doong
    //sau do luu ca salt va hash passord vao csdl
    private String salt = "01X-343n42mnl3905u";
    @Override
    public String hashPassword(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());

            byte[] bytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i <bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                .substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    @Override
    public boolean validatePassword(String originalPassword, String storedPassword) {
        String hashed_password = hashPassword(originalPassword);
        return storedPassword.equals(hashed_password);
    }
}
