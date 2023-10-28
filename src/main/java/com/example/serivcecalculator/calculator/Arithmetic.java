package com.example.serivcecalculator.calculator;

import com.example.serivcecalculator.exception.SyntaxError;

public class Arithmetic {

    public static int expression(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) {
            return 0;
        } else {
            lexemes.back();
            return additionSubtraction(lexemes);
        }
    }

    public static int additionSubtraction(LexemeBuffer lexemes) {
        int value = multiplicationDivision(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS:
                    value += multiplicationDivision(lexemes);
                    break;
                case OP_MINUS:
                    value -= multiplicationDivision(lexemes);
                default:
                    lexemes.back();
                    return value;
            }
        }
    }

    public static int multiplicationDivision(LexemeBuffer lexemes) {
        int value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL:
                    value *= factor(lexemes);
                    break;
                case OP_DIV:
                    value /= factor(lexemes);
                default:
                    lexemes.back();
                    return value;
            }
        }
    }

    public static int factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        int value;
        switch (lexeme.type) {
            case OP_MINUS:
            value = factor(lexemes);
            return -value;
            case NUMBER:
                return Integer.parseInt(lexeme.value);
            case LEFT_BRACKET:
                value = expression(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new SyntaxError("Unexpected character " + lexeme.value
                            + " at position: " + lexemes.getPos());
                }
                return value;
            default:
                throw new SyntaxError("Unexpected character" + lexeme.value
                        + "at position: " + lexemes.getPos());
        }
    }
}
