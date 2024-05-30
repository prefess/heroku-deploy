package com.example.SpringBootDemoProject.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/*
https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 */

public interface Hashing {
    public String hashPassword(String password);

    public boolean validatePassword(String originalPassword, String storedPassword);
}
