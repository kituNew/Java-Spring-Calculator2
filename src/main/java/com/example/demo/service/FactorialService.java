package com.example.demo.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

@Service
public class FactorialService {

    public BigInteger factorial(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Факториал от отрицательного числа вычислять нельзя");
        }

        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= value; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
