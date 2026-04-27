package com.example.hw4.calculator.strategy.binary;

public interface OperationStrategy {
    String getName();

    double apply(double a, double b);
}
