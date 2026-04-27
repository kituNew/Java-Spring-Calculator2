package com.example.hw4.calculator.service;

import com.example.hw4.calculator.i18n.Messages;
import com.example.hw4.calculator.strategy.binary.OperationStrategy;
import com.example.hw4.calculator.strategy.unary.UnaryOperationStrategy;
import org.springframework.stereotype.Service;

@Service
public class OperationExecutor {

    private final Messages messages;

    public OperationExecutor(Messages messages) {
        this.messages = messages;
    }

    public double execute(OperationStrategy strategy, double a, double b) {
        if (strategy == null) {
            throw new IllegalArgumentException(messages.get("error.operation.unknown-binary"));
        }
        return strategy.apply(a, b);
    }

    public double execute(UnaryOperationStrategy strategy, double a) {
        if (strategy == null) {
            throw new IllegalArgumentException(messages.get("error.operation.unknown-unary"));
        }
        return strategy.apply(a);
    }
}
