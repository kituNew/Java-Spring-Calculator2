package com.example.demo.strategy.unary;

import org.springframework.stereotype.Component;

@Component
public class FactorialOperation implements UnaryOperationStrategy {

    @Override
    public String getName() {
        return "!";
    }

    @Override
    public double apply(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Нельзя взять отрицательный факториал");
        }
        if (a != Math.floor(a)) {
            throw new IllegalArgumentException("Факториал определён только для целых чисел");
        }
        if (a > 170) {
            throw new IllegalArgumentException("Слишком большое число");
        }
        return factorial(a);
    }

    private double factorial(double a) {
        if (a <= 1) {
            return 1;
        }
        return a * factorial(a-1);
    }
}
