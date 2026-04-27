package com.example.hw4.calculator.service;

import java.math.BigInteger;

import com.example.hw4.calculator.i18n.Messages;
import org.springframework.stereotype.Service;

@Service
public class FactorialService {

    private final Messages messages;

    public FactorialService(Messages messages) {
        this.messages = messages;
    }

    public BigInteger factorial(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(messages.get("error.factorial.negative"));
        }

        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= value; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
