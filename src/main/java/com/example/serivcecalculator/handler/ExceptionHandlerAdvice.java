package com.example.serivcecalculator.handler;

import com.example.serivcecalculator.exception.SyntaxError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(SyntaxError.class)
    public ResponseEntity<String> syntaxError(SyntaxError e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
