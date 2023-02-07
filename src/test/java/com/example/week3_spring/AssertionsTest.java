package com.example.week3_spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionsTest {

    private final Calculator calculator = new Calculator();
//    private final Person person = new Person("동현", "민");

//    @DisplayName("asser")
    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(4, calculator.multiply(2, 2),
                "추가적인 실패 메세지는 마지막 파라미터에 넣는다.");
        assertTrue('a' < 'b', () -> "Assertion messages는 지연로딩과 비슷하게 동작한다. -- "
                + "불필요하게 메세지를 만드는 일을 피하기 위해서.");
    }

}
