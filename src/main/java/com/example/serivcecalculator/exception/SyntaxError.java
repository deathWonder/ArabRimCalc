package com.example.serivcecalculator.exception;

public class SyntaxError extends IllegalArgumentException {
    public SyntaxError(String message) {
        super(message);
    }
}
