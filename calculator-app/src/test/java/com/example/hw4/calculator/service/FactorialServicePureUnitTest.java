package com.example.hw4.calculator.service;

import java.math.BigInteger;

import com.example.hw4.calculator.i18n.Messages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactorialServicePureUnitTest {

    private final Messages messages = (code, args) -> code;
    private final FactorialService factorialService = new FactorialService(messages);

    @Test
    void shouldCalculateFactorial() {
        assertEquals(BigInteger.valueOf(120), factorialService.factorial(5));
    }

    @Test
    void shouldRejectNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> factorialService.factorial(-1));
    }
}
