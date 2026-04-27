package com.example.hw4.calculator.shell;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.List;

import com.example.hw4.calculator.i18n.Messages;
import com.example.hw4.calculator.service.ExpressionEvaluator;
import com.example.hw4.calculator.service.FactorialService;
import com.example.hw4.calculator.service.GreetingService;
import com.example.hw4.calculator.service.OperationExecutor;
import com.example.hw4.calculator.service.OperationNavigator;
import com.example.hw4.io.IoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = CalculatorShellCommandsBootTest.Config.class,
        properties = {
                "spring.shell.interactive.enabled=false",
                "app.expression.max-length=20"
        }
)
class CalculatorShellCommandsBootTest {

    @Autowired
    private CalculatorShellCommands commands;

    @Autowired
    private ExpressionEvaluator expressionEvaluator;

    @Autowired
    private FactorialService factorialService;

    @Autowired
    private GreetingService greetingService;

    @Autowired
    private OperationNavigator operationNavigator;

    @Autowired
    private OperationExecutor operationExecutor;

    @Autowired
    private IoService ioService;

    @Autowired
    private Messages messages;

    @BeforeEach
    void setUp() {
        reset(expressionEvaluator, factorialService, greetingService, operationNavigator, operationExecutor, ioService, messages);
    }

    @Test
    void shouldPrintLocalizedExpressionResultUsingMocks() {
        when(expressionEvaluator.evaluate("2+2")).thenReturn(4.0);
        when(messages.get("shell.result.expression", "2+2", 4.0)).thenReturn("Результат выражения \"2+2\": 4.0");

        commands.expression("2+2");

        verify(ioService).println("Результат выражения \"2+2\": 4.0");
    }

    @Test
    void shouldPrintFactorialResultUsingMocks() {
        when(factorialService.factorial(5)).thenReturn(BigInteger.valueOf(120));
        when(messages.get("shell.result.factorial", 5, BigInteger.valueOf(120))).thenReturn("Факториал 5: 120");

        commands.factorial(5);

        verify(ioService).println("Факториал 5: 120");
    }

    @Test
    void shouldPrintGreetingUsingMocks() {
        when(greetingService.greeting()).thenReturn("Здравствуйте, милорд Влад!");

        commands.greet();

        verify(ioService).println("Здравствуйте, милорд Влад!");
    }

    @Test
    void shouldPrintAvailableOperationsUsingMocks() {
        when(operationNavigator.getAvailableOperations()).thenReturn(new LinkedHashSet<>(List.of("+", "!")));
        when(messages.get("shell.available.operations", "+, !")).thenReturn("Доступные операции: +, !");

        commands.operations();

        verify(ioService).println("Доступные операции: +, !");
    }

    @Configuration
    static class Config {

        @Bean
        CalculatorShellCommands calculatorShellCommands(
                ExpressionEvaluator expressionEvaluator,
                FactorialService factorialService,
                GreetingService greetingService,
                OperationNavigator operationNavigator,
                OperationExecutor operationExecutor,
                IoService ioService,
                Messages messages
        ) {
            return new CalculatorShellCommands(
                    expressionEvaluator,
                    factorialService,
                    greetingService,
                    operationNavigator,
                    operationExecutor,
                    ioService,
                    messages
            );
        }

        @Bean
        ExpressionEvaluator expressionEvaluator() {
            return mock(ExpressionEvaluator.class);
        }

        @Bean
        FactorialService factorialService() {
            return mock(FactorialService.class);
        }

        @Bean
        GreetingService greetingService() {
            return mock(GreetingService.class);
        }

        @Bean
        OperationNavigator operationNavigator() {
            return mock(OperationNavigator.class);
        }

        @Bean
        OperationExecutor operationExecutor() {
            return mock(OperationExecutor.class);
        }

        @Bean
        IoService ioService() {
            return mock(IoService.class);
        }

        @Bean
        Messages messages() {
            return mock(Messages.class);
        }
    }
}
