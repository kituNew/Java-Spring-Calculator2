package com.example.hw4.calculator.service;

import java.util.Set;

import com.example.hw4.calculator.strategy.binary.OperationStrategy;
import com.example.hw4.calculator.strategy.unary.UnaryOperationStrategy;

public interface OperationNavigator {
    boolean isUnary(String operation);

    boolean isBinary(String operation);

    OperationStrategy getBinary(String operation);

    UnaryOperationStrategy getUnary(String operation);

    Set<String> getAvailableOperations();
}
