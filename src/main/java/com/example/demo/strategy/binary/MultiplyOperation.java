package com.example.demo.strategy.binary;

import org.springframework.stereotype.Component;

@Component
public class MultiplyOperation implements OperationStrategy {

    @Override
    public String getName() {
        return "*";
    }

    @Override
    public double apply(double a, double b) {
        return a * b;
    }
}
