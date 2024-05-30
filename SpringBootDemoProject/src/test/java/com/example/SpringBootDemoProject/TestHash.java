package com.example.SpringBootDemoProject;

import com.example.SpringBootDemoProject.security.Hashing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestHash {
    @Autowired private Hashing hash;

    @Test
    public void hashPassword() {
        var passwords = List.of("abc", "qwert", "0x-123", "_$#@LLk2312");
        for (String password:
             passwords) {
            String hashed_pass = hash.hashPassword(password);
            assertThat(hashed_pass).isNotNull();
        }
    }

    @Test
    public void validatePassword() {
        var passwords = List.of("abc1232-+", "qwert1903", "0x-123", "_$#@LLk2312");
        for (String password:passwords) {
            String hashed_pass = hash.hashPassword(password);

            assertThat(hash.validatePassword(password,hashed_pass)).isTrue();
        }
        assertThat(hash.validatePassword("abc", "123:bcv:acs")).isFalse();
    }
}
