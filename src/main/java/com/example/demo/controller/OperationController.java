package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ExpressionEvaluator;
import com.example.demo.service.FactorialService;
import com.example.demo.service.GreetingService;

@RestController
public class OperationController {

    private final ExpressionEvaluator expressionEvaluator;
    private final FactorialService factorialService;
    private final GreetingService greetingService;

    public OperationController(
            ExpressionEvaluator expressionEvaluator,
            FactorialService factorialService,
            GreetingService greetingService
    ) {
        this.expressionEvaluator = expressionEvaluator;
        this.factorialService = factorialService;
        this.greetingService = greetingService;
    }

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("status", "ok");
    }

    @GetMapping("/calc/expression")
    public Map<String, Object> calculateExpression(@RequestParam String value) {
        return Map.of(
                "type", "expression",
                "expression", value,
                "result", expressionEvaluator.evaluate(value)
        );
    }

    @GetMapping("/calc/factorial")
    public Map<String, Object> calculateFactorial(@RequestParam int value) {
        return Map.of(
                "type", "factorial",
                "value", value,
                "result", factorialService.factorial(value).toString()
        );
    }

    @GetMapping("/calc/greeting")
    public Map<String, Object> greeting() {
        return Map.of(
                "message", greetingService.greeting(),
                "user", greetingService.getCurrentUser().displayName()
        );
    }
}
