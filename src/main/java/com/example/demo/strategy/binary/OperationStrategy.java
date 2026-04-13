package com.example.demo.strategy.binary;

public interface OperationStrategy {
    String getName();
    double apply(double a, double b);
}
