package com.example.hw4.calculator.strategy.unary;

import com.example.hw4.calculator.i18n.Messages;
import org.springframework.stereotype.Component;

@Component
public class FactorialOperation implements UnaryOperationStrategy {

    private final Messages messages;

    public FactorialOperation(Messages messages) {
        this.messages = messages;
    }

    @Override
    public String getName() {
        return "!";
    }

    @Override
    public double apply(double a) {
        if (a < 0) {
            throw new IllegalArgumentException(messages.get("error.factorial.negative"));
        }
        if (a != Math.floor(a)) {
            throw new IllegalArgumentException(messages.get("error.factorial.not-integer"));
        }
        if (a > 170) {
            throw new IllegalArgumentException(messages.get("error.factorial.too-big"));
        }
        return factorial((int) a);
    }

    private double factorial(int value) {
        double result = 1;
        for (int i = 2; i <= value; i++) {
            result *= i;
        }
        return result;
    }
}
