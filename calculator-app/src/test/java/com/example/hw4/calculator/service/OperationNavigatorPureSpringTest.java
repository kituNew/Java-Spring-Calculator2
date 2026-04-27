package com.example.hw4.calculator.service;

import java.util.List;

import com.example.hw4.calculator.i18n.Messages;
import com.example.hw4.calculator.strategy.binary.AddOperation;
import com.example.hw4.calculator.strategy.binary.DivideOperation;
import com.example.hw4.calculator.strategy.binary.MultiplyOperation;
import com.example.hw4.calculator.strategy.binary.OperationStrategy;
import com.example.hw4.calculator.strategy.binary.SubtractOperation;
import com.example.hw4.calculator.strategy.unary.FactorialOperation;
import com.example.hw4.calculator.strategy.unary.UnaryOperationStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@ContextConfiguration(classes = OperationNavigatorPureSpringTest.Config.class)
class OperationNavigatorPureSpringTest {

    @Autowired
    private OperationNavigator operationNavigator;

    @Test
    void shouldFindBinaryAndUnaryOperationsInNarrowSpringContext() {
        assertThat(operationNavigator.isBinary("+")).isTrue();
        assertThat(operationNavigator.isBinary("/")).isTrue();
        assertThat(operationNavigator.isUnary("!")).isTrue();
        assertThat(operationNavigator.getAvailableOperations()).containsExactlyInAnyOrder("+", "-", "*", "/", "!");
    }

    @Configuration
    static class Config {

        @Bean
        Messages messages() {
            return (code, args) -> code;
        }

        @Bean
        AddOperation addOperation() {
            return new AddOperation();
        }

        @Bean
        SubtractOperation subtractOperation() {
            return new SubtractOperation();
        }

        @Bean
        MultiplyOperation multiplyOperation() {
            return new MultiplyOperation();
        }

        @Bean
        DivideOperation divideOperation(Messages messages) {
            return new DivideOperation(messages);
        }

        @Bean
        FactorialOperation factorialOperation(Messages messages) {
            return new FactorialOperation(messages);
        }

        @Bean
        OperationNavigator operationNavigator(
                List<OperationStrategy> binaryStrategies,
                List<UnaryOperationStrategy> unaryStrategies
        ) {
            return new OperationNavigatorImpl(binaryStrategies, unaryStrategies);
        }
    }
}
