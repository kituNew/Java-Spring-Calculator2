package com.example.demo.service;

import java.util.Set;

import com.example.demo.strategy.binary.OperationStrategy;
import com.example.demo.strategy.unary.UnaryOperationStrategy;

public interface OperationNavigator {
    boolean isUnary(String operation);
    boolean isBinary(String operation);

    OperationStrategy getBinary(String operation);
    UnaryOperationStrategy getUnary(String operation);

    Set<String> getAvailableOperations();
}