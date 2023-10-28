package com.example.serivcecalculator.calculator;

import com.example.serivcecalculator.exception.SyntaxError;

import java.util.List;

public class Calculator {

    public String solve(String expression, boolean isArabic) {
        if (isArabic) {
            if (Converter.checkExpressionArabic(expression)) {
                LexemeBuffer buffer = new LexemeBuffer(Analyzer.lexemeAnalyzerArab(expression));
                return String.valueOf(Arithmetic.expression(buffer));
            } else {
                throw new SyntaxError("Unexpected expression!");
            }
        } else {
            if (Converter.checkExpressionRoma(expression)) {
                List<Lexeme> arabLexemes = Converter.preparingRomaNum(Analyzer.lexemeAnalyzerRoma(expression));
                LexemeBuffer buffer = new LexemeBuffer(arabLexemes);
                return Converter.convertToRoma(Arithmetic.expression(buffer));
            } else {
                throw new SyntaxError("Unexpected expression!");
            }
        }
    }
}
