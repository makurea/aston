package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactorialCalculatorTest {

    private final FactorialCalculator calculator = new FactorialCalculator();

    @Test
    public void testFactorialOfZero() {
        assertEquals(1, calculator.factorial(0));
    }

    @Test
    public void testFactorialOfOne() {
        assertEquals(1, calculator.factorial(1));
    }

    @Test
    public void testFactorialOfFive() {
        assertEquals(120, calculator.factorial(5));
    }

    @Test
    public void testNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-1));
    }
}
