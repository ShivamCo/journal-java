package com.journal.journal.service;

import com.journal.journal.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServicesTest {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void testFindByUserName(){
       ;
        assertEquals(4,2+2);
        assertNotNull(userRepository.findByUserName("Second"));
    }


    @ParameterizedTest
    @CsvSource({
            "2,2,4",
            "50,90,140",
            "1,1,2"
    })
    public void test(int a, int b, int expected){
            assertEquals(expected, a + b);
    }

}
