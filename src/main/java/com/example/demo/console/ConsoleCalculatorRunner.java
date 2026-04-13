package com.example.demo.console;

import java.math.BigInteger;
import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import com.example.demo.service.ExpressionEvaluator;
import com.example.demo.service.FactorialService;
import com.example.demo.service.GreetingService;

@Component
public class ConsoleCalculatorRunner implements CommandLineRunner {

    private final IoService ioService;
    private final GreetingService greetingService;
    private final ExpressionEvaluator expressionEvaluator;
    private final FactorialService factorialService;

    public ConsoleCalculatorRunner(
            IoService ioService,
            GreetingService greetingService,
            ExpressionEvaluator expressionEvaluator,
            FactorialService factorialService
    ) {
        this.ioService = ioService;
        this.greetingService = greetingService;
        this.expressionEvaluator = expressionEvaluator;
        this.factorialService = factorialService;
    }

    @Override
    public void run(String... args) {
        boolean consoleMode = Arrays.asList(args).contains("console");
        if (!consoleMode) {
            return;
        }

        ioService.println(greetingService.greeting());
        ioService.println("Консольный калькулятор запущен.");
        ioService.println("Максимальная длина выражения: " + expressionEvaluator.getMaxExpressionLength());
        ioService.println("1 - вычислить арифметическое выражение");
        ioService.println("2 - вычислить факториал");
        ioService.println("0 / exit - выйти");

        while (true) {
            ioService.print("\nВыберите действие: ");
            String action = ioService.readLine().trim();

            if (action.equalsIgnoreCase("exit") || action.equals("0")) {
                ioService.println("Выход из программы.");
                break;
            }

            try {
                switch (action) {
                    case "1" -> handleExpression();
                    case "2" -> handleFactorial();
                    default -> ioService.println("Некорректный ввод. Введите 1, 2, 0 или exit.");
                }
            } catch (IllegalArgumentException e) {
                ioService.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void handleExpression() {
        ioService.print("Введите арифметическое выражение: ");
        String expression = ioService.readLine();
        double result = expressionEvaluator.evaluate(expression);
        ioService.println("Результат: " + result);
    }

    private void handleFactorial() {
        ioService.print("Введите целое неотрицательное число: ");
        String rawValue = ioService.readLine().trim();

        int value;
        try {
            value = Integer.parseInt(rawValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректный ввод: ожидалось целое число", e);
        }

        BigInteger result = factorialService.factorial(value);
        ioService.println("Результат: " + result);
    }
}
