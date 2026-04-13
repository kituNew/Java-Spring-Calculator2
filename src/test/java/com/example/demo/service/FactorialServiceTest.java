package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class FactorialServiceTest {

    private final FactorialService factorialService = new FactorialService();

    @Test
    void shouldCalculateFactorial() {
        assertEquals(BigInteger.valueOf(120), factorialService.factorial(5));
    }

    @Test
    void shouldRejectNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> factorialService.factorial(-1));
    }
}
