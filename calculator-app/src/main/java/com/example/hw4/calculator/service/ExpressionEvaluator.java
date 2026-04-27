package com.example.hw4.calculator.service;

import java.util.regex.Pattern;

import com.example.hw4.calculator.i18n.Messages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

@Service
public class ExpressionEvaluator {

    private static final Pattern ALLOWED_SYMBOLS = Pattern.compile("[0-9+\\-*/().\\s]+$");
    private static final Pattern INTEGER_TOKEN = Pattern.compile("(?<![\\d.])(\\d+)(?![\\d.])");

    private final ExpressionParser expressionParser;
    private final int maxExpressionLength;
    private final Messages messages;

    public ExpressionEvaluator(
            ExpressionParser expressionParser,
            @Value("#{T(java.lang.Math).max(${app.expression.max-length:100}, 1)}") int maxExpressionLength,
            Messages messages
    ) {
        this.expressionParser = expressionParser;
        this.maxExpressionLength = maxExpressionLength;
        this.messages = messages;
    }

    public double evaluate(String expression) {
        validateExpression(expression);

        String preparedExpression = prepareForSpel(expression);
        try {
            Double result = expressionParser.parseExpression(preparedExpression).getValue(Double.class);
            if (result == null || result.isNaN() || result.isInfinite()) {
                throw new IllegalArgumentException(messages.get("error.expression.cannot-evaluate"));
            }
            return result;
        } catch (ParseException | EvaluationException e) {
            throw new IllegalArgumentException(messages.get("error.expression.invalid"), e);
        }
    }

    public int getMaxExpressionLength() {
        return maxExpressionLength;
    }

    private void validateExpression(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException(messages.get("error.expression.blank"));
        }

        String normalized = expression.replaceAll("\\s+", "");
        if (normalized.length() > maxExpressionLength) {
            throw new IllegalArgumentException(
                    messages.get("error.expression.too-long", maxExpressionLength)
            );
        }

        if (!ALLOWED_SYMBOLS.matcher(expression).matches()) {
            throw new IllegalArgumentException(messages.get("error.expression.invalid"));
        }
    }

    private String prepareForSpel(String expression) {
        return INTEGER_TOKEN.matcher(expression).replaceAll("$1.0");
    }
}
