package com.example.serivcecalculator.calculator;

import com.example.serivcecalculator.exception.SyntaxError;

import java.util.ArrayList;
import java.util.List;

public class Analyzer {

    public static List<Lexeme> lexemeAnalyzerArab(String expression) {

        List<Lexeme> lexemes = new ArrayList<>();

        int pos = 0;

        while (pos < expression.length()) {
            while (pos < expression.length()) {
                char character = expression.charAt(pos);
                switch (character) {
                    case '(':
                        lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, character));
                        ++pos;
                        continue;
                    case ')':
                        lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, character));
                        ++pos;
                        continue;
                    case '*':
                        lexemes.add(new Lexeme(LexemeType.OP_MUL, character));
                        ++pos;
                        continue;
                    case '+':
                        lexemes.add(new Lexeme(LexemeType.OP_PLUS, character));
                        ++pos;
                        continue;
                    case '-':
                        lexemes.add(new Lexeme(LexemeType.OP_MINUS, character));
                        ++pos;
                        continue;
                    case '/':
                        lexemes.add(new Lexeme(LexemeType.OP_DIV, character));
                        ++pos;
                        continue;
                    default:
                        if (character <= '9' && character >= '0') {
                            StringBuilder sb = new StringBuilder();
                            do {
                                sb.append(character);
                                ++pos;
                                if (pos >= expression.length()) {
                                    break;
                                }
                                character = expression.charAt(pos);
                            } while (character <= '9' && character >= '0');

                            lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));

                        } else {
                            if (character != ' ') {
                                throw new SyntaxError("Unexpected character: " + character);
                            }
                            ++pos;
                        }
                }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    public static List<Lexeme> lexemeAnalyzerRoma(String expression) {

        List<Lexeme> lexemes = new ArrayList<>();

        int pos = 0;

        while (pos < expression.length()) {
            while (pos < expression.length()) {
                char character = expression.charAt(pos);
                switch (character) {
                    case '(':
                        lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, character));
                        ++pos;
                        continue;
                    case ')':
                        lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, character));
                        ++pos;
                        continue;
                    case '*':
                        lexemes.add(new Lexeme(LexemeType.OP_MUL, character));
                        ++pos;
                        continue;
                    case '+':
                        lexemes.add(new Lexeme(LexemeType.OP_PLUS, character));
                        ++pos;
                        continue;
                    case '-':
                        lexemes.add(new Lexeme(LexemeType.OP_MINUS, character));
                        ++pos;
                        continue;
                    case '/':
                        lexemes.add(new Lexeme(LexemeType.OP_DIV, character));
                        ++pos;
                        continue;
                    default:
                        if (Converter.isRoma(character)) {
                            StringBuilder sb = new StringBuilder();
                            do {
                                sb.append(character);
                                ++pos;
                                if (pos >= expression.length()) {
                                    break;
                                }
                                character = expression.charAt(pos);

                            } while (Converter.isRoma(character));

                            lexemes.add(new Lexeme(LexemeType.ROMA_NUM, sb.toString()));

                        } else {
                            if (character != ' ') {
                                throw new SyntaxError("Unexpected character: " + character);
                            }
                            ++pos;
                        }
                }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

}

