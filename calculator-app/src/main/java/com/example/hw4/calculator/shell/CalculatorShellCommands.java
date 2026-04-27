package com.example.hw4.calculator.shell;

import java.math.BigInteger;
import java.util.stream.Collectors;

import com.example.hw4.calculator.i18n.Messages;
import com.example.hw4.calculator.service.ExpressionEvaluator;
import com.example.hw4.calculator.service.FactorialService;
import com.example.hw4.calculator.service.GreetingService;
import com.example.hw4.calculator.service.OperationExecutor;
import com.example.hw4.calculator.service.OperationNavigator;
import com.example.hw4.io.IoService;
import org.springframework.shell.core.command.annotation.Command;
import org.springframework.shell.core.command.annotation.Option;
import org.springframework.stereotype.Component;

@Component
public class CalculatorShellCommands {

    private final ExpressionEvaluator expressionEvaluator;
    private final FactorialService factorialService;
    private final GreetingService greetingService;
    private final OperationNavigator operationNavigator;
    private final OperationExecutor operationExecutor;
    private final IoService ioService;
    private final Messages messages;

    public CalculatorShellCommands(
            ExpressionEvaluator expressionEvaluator,
            FactorialService factorialService,
            GreetingService greetingService,
            OperationNavigator operationNavigator,
            OperationExecutor operationExecutor,
            IoService ioService,
            Messages messages
    ) {
        this.expressionEvaluator = expressionEvaluator;
        this.factorialService = factorialService;
        this.greetingService = greetingService;
        this.operationNavigator = operationNavigator;
        this.operationExecutor = operationExecutor;
        this.ioService = ioService;
        this.messages = messages;
    }

    @Command(name = "greet", description = "Print localized greeting.", group = "Calculator")
    public void greet() {
        ioService.println(greetingService.greeting());
    }

    @Command(name = "expr", description = "Evaluate arithmetic expression.", group = "Calculator")
    public void expression(
            @Option(shortName = 'v', longName = "value", description = "Expression, for example: '(2 + 3) * 4'")
            String expression
    ) {
        double result = expressionEvaluator.evaluate(expression);
        ioService.println(messages.get("shell.result.expression", expression, result));
    }

    @Command(name = "fact", description = "Calculate factorial.", group = "Calculator")
    public void factorial(
            @Option(shortName = 'v', longName = "value", description = "Integer non-negative value")
            int value
    ) {
        BigInteger result = factorialService.factorial(value);
        ioService.println(messages.get("shell.result.factorial", value, result));
    }

    @Command(name = "calc", description = "Calculate binary operation by strategy.", group = "Calculator")
    public void calculate(
            @Option(longName = "left", description = "Left argument") double left,
            @Option(longName = "op", description = "Operation: +, -, *, /") String operation,
            @Option(longName = "right", description = "Right argument") double right
    ) {
        double result = operationExecutor.execute(operationNavigator.getBinary(operation), left, right);
        ioService.println(messages.get("shell.result.binary", left, operation, right, result));
    }

    @Command(name = "operations", description = "Show available calculator operations.", group = "Calculator")
    public void operations() {
        String operations = operationNavigator.getAvailableOperations()
                .stream()
                .collect(Collectors.joining(", "));
        ioService.println(messages.get("shell.available.operations", operations));
    }
}
