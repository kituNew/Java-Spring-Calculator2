package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.strategy.binary.OperationStrategy;
import com.example.demo.strategy.unary.UnaryOperationStrategy;

@Service
public class OperationExecutor {

    public double execute(OperationStrategy strategy, double a, double b) {
        if (strategy == null) {
            throw new IllegalArgumentException("Неизвестная бинарная операция");
        }
        return strategy.apply(a, b);
    }

    public double execute(UnaryOperationStrategy strategy, double a) {
        if (strategy == null) {
            throw new IllegalArgumentException("Неизвестная унарная операция");
        }
        return strategy.apply(a);
    }
}