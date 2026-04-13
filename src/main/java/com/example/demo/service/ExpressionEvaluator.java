package com.example.demo.service;

import java.util.regex.Pattern;

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

    public ExpressionEvaluator(
            ExpressionParser expressionParser,
            @Value("#{T(java.lang.Math).max(${app.expression.max-length}, 1)}") int maxExpressionLength
    ) {
        this.expressionParser = expressionParser;
        this.maxExpressionLength = maxExpressionLength;
    }

    public double evaluate(String expression) {
        validateExpression(expression);

        String preparedExpression = prepareForSpel(expression);
        try {
            Double result = expressionParser.parseExpression(preparedExpression).getValue(Double.class);
            if (result == null || result.isNaN() || result.isInfinite()) {
                throw new IllegalArgumentException("Не удалось вычислить выражение");
            }
            return result;
        } catch (ParseException | EvaluationException e) {
            throw new IllegalArgumentException("Некорректное арифметическое выражение", e);
        }
    }

    public int getMaxExpressionLength() {
        return maxExpressionLength;
    }

    private void validateExpression(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException("Пустое арифметическое выражение");
        }

        String normalized = expression.replaceAll("\\s+", "");
        if (normalized.length() > maxExpressionLength) {
            throw new IllegalArgumentException(
                    "Превышена максимальная длина выражения: " + maxExpressionLength
            );
        }

        if (!ALLOWED_SYMBOLS.matcher(expression).matches()) {
            throw new IllegalArgumentException("Некорректное арифметическое выражение");
        }
    }

    private String prepareForSpel(String expression) {
        return INTEGER_TOKEN.matcher(expression).replaceAll("$1.0");
    }
}
