package com.example.hw4.calculator.service;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.hw4.calculator.strategy.binary.OperationStrategy;
import com.example.hw4.calculator.strategy.unary.UnaryOperationStrategy;
import org.springframework.stereotype.Service;

@Service
public class OperationNavigatorImpl implements OperationNavigator {

    private final Map<String, OperationStrategy> binaryStrategies;
    private final Map<String, UnaryOperationStrategy> unaryStrategies;

    public OperationNavigatorImpl(
            List<OperationStrategy> binaryStrategies,
            List<UnaryOperationStrategy> unaryStrategies
    ) {
        this.binaryStrategies = new LinkedHashMap<>();
        for (OperationStrategy strategy : binaryStrategies) {
            this.binaryStrategies.put(strategy.getName(), strategy);
        }

        this.unaryStrategies = new LinkedHashMap<>();
        for (UnaryOperationStrategy strategy : unaryStrategies) {
            this.unaryStrategies.put(strategy.getName(), strategy);
        }
    }

    @Override
    public boolean isUnary(String operation) {
        return unaryStrategies.containsKey(operation);
    }

    @Override
    public boolean isBinary(String operation) {
        return binaryStrategies.containsKey(operation);
    }

    @Override
    public OperationStrategy getBinary(String operation) {
        return binaryStrategies.get(operation);
    }

    @Override
    public UnaryOperationStrategy getUnary(String operation) {
        return unaryStrategies.get(operation);
    }

    @Override
    public Set<String> getAvailableOperations() {
        Set<String> result = new LinkedHashSet<>();
        result.addAll(binaryStrategies.keySet());
        result.addAll(unaryStrategies.keySet());
        return result;
    }
}
