package com.example.hw4.calculator.strategy.unary;

public interface UnaryOperationStrategy {
    String getName();

    double apply(double a);
}
