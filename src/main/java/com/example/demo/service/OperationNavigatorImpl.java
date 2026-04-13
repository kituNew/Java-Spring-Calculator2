package com.example.demo.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.strategy.binary.OperationStrategy;
import com.example.demo.strategy.unary.UnaryOperationStrategy;

@Service
@Profile("annotations-default")
public class OperationNavigatorImpl implements OperationNavigator {

    private final Map<String, OperationStrategy> binaryStrategies;
    private final Map<String, UnaryOperationStrategy> unaryStrategies;

    public OperationNavigatorImpl(
            List<OperationStrategy> binaryStrategies,
            List<UnaryOperationStrategy> unaryStrategies
    ) {
        this.binaryStrategies = binaryStrategies.stream()
                .collect(Collectors.toMap(OperationStrategy::getName, Function.identity()));

        this.unaryStrategies = unaryStrategies.stream()
                .collect(Collectors.toMap(UnaryOperationStrategy::getName, Function.identity()));
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