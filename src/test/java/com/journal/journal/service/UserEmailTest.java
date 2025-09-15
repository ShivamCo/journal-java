package com.journal.journal.service;

import com.journal.journal.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEmailTest {

    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @Test
    public void userEmailTest(){
        Assertions.assertNotNull(userRepositoryImpl.getUserForSA());
    }

}
