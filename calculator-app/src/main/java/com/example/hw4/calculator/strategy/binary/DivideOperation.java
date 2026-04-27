package com.example.hw4.calculator.strategy.binary;

import com.example.hw4.calculator.i18n.Messages;
import org.springframework.stereotype.Component;

@Component
public class DivideOperation implements OperationStrategy {

    private final Messages messages;

    public DivideOperation(Messages messages) {
        this.messages = messages;
    }

    @Override
    public String getName() {
        return "/";
    }

    @Override
    public double apply(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException(messages.get("error.division-by-zero"));
        }
        return a / b;
    }
}
