package com.example;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class FactorialCalculatorTest {

    private final FactorialCalculator calculator = new FactorialCalculator();

    @Test
    public void testFactorialOfZero() {
        assertEquals(calculator.factorial(0), 1);
    }

    @Test
    public void testFactorialOfOne() {
        assertEquals(calculator.factorial(1), 1);
    }

    @Test
    public void testFactorialOfFive() {
        assertEquals(calculator.factorial(5), 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeNumber() {
        calculator.factorial(-1);
    }
}
