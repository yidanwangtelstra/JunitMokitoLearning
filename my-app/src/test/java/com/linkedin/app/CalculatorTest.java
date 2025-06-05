package com.linkedin.app;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void addTest(){
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
    }

    @Test
    public void divisionTest(){
        int result = calculator.divide(6, 2);
        assertNotEquals(0,  result, "result should not be zero");
    }

    @Test
    public void isEvenTest(){
        boolean result = calculator.isEven(4);
        assertTrue(result, "the number should be even");
    }

    @Test
    public void isEven_withOddNumberTest(){
        boolean result = calculator.isEven(3);
        assertFalse(result, "the number should be odd");
    }

    @Test
    public void divideByZeroTest(){
        Integer result = null;
        try {
            result = calculator.divide(0, 0);
        } catch (ArithmeticException e) {
        }
        assertNull(result);
    }

    @Test
    void devideNonZeroTest(){
        Integer result = calculator.divide(10, 2);
        assertNotNull(result);
        assertEquals(5, result);
    }

}