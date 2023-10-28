package com.example.serivcecalculator.controller;

import com.example.serivcecalculator.service.SolveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class SolveController {

    private final SolveService service;

    public SolveController(SolveService service) {
        this.service = service;
    }

    @PostMapping("/solve")
    public ResponseEntity<String> solveExpression(@RequestParam String expression,
                                                  @RequestParam String numType) {
        return ResponseEntity.ok(service.solveExpression(expression, numType));
    }
}
