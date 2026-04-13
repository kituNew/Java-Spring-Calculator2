package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.expression.spel.standard.SpelExpressionParser;

class ExpressionEvaluatorTest {

    private ExpressionEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new ExpressionEvaluator(new SpelExpressionParser(), 10);
    }

    @Test
    void shouldEvaluateExpressionWithParentheses() {
        double result = evaluator.evaluate("(2 + 3) * 4");
        assertEquals(20.0, result);
    }

    @Test
    void shouldEvaluateFractionAsDouble() {
        double result = evaluator.evaluate("2/4");
        assertEquals(0.5, result);
    }

    @Test
    void shouldRejectTooLongExpression() {
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate("12345678901"));
    }
}
