package com.example.demo.strategy.binary;

import org.springframework.stereotype.Component;

@Component
public class DivideOperation implements OperationStrategy {

    @Override
    public String getName() {
        return "/";
    }

    @Override
    public double apply(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Делить на 0 нельзя");
        }
        return a / b;
    }
}
