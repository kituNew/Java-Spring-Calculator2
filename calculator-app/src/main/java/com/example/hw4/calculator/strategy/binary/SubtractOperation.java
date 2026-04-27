package com.example.hw4.calculator.strategy.binary;

import org.springframework.stereotype.Component;

@Component
public class SubtractOperation implements OperationStrategy {

    @Override
    public String getName() {
        return "-";
    }

    @Override
    public double apply(double a, double b) {
        return a - b;
    }
}
