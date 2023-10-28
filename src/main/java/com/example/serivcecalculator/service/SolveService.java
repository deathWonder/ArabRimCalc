package com.example.serivcecalculator.service;

import com.example.serivcecalculator.calculator.Calculator;
import org.springframework.stereotype.Service;

@Service
public class SolveService {
    private final Calculator calculator = new Calculator();

    public String solveExpression(String expression, String numType) {
        if (numType.equals("arabic")) {
            return calculator.solve(expression, true);
        } else {
            return calculator.solve(expression, false);
        }
    }
}
