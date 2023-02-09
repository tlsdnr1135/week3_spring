package com.example.week3_spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class Week3SpringApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("아메리카노");
    }

}
