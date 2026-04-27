package com.example.hw4.calculator.service;

import com.example.hw4.calculator.i18n.Messages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig
@ContextConfiguration(classes = ExpressionEvaluatorPureSpringTest.Config.class)
@TestPropertySource(properties = "app.expression.max-length=10")
class ExpressionEvaluatorPureSpringTest {

    @Autowired
    private ExpressionEvaluator evaluator;

    @Test
    void shouldEvaluateExpressionWithParentheses() {
        assertEquals(20.0, evaluator.evaluate("(2 + 3) * 4"));
    }

    @Test
    void shouldEvaluateFractionAsDouble() {
        assertEquals(0.5, evaluator.evaluate("2/4"));
    }

    @Test
    void shouldRejectTooLongExpression() {
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate("12345678901"));
    }

    @Configuration
    static class Config {

        @Bean
        ExpressionParser expressionParser() {
            return new SpelExpressionParser();
        }

        @Bean
        Messages messages() {
            return (code, args) -> code;
        }

        @Bean
        ExpressionEvaluator expressionEvaluator(
                ExpressionParser expressionParser,
                @Value("${app.expression.max-length}") int maxExpressionLength,
                Messages messages
        ) {
            return new ExpressionEvaluator(expressionParser, maxExpressionLength, messages);
        }
    }
}
