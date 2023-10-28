package com.example.serivcecalculator.calculator;

import com.example.serivcecalculator.exception.SyntaxError;

import java.util.HashMap;
import java.util.List;

public class Converter {


    public static String convertToRoma(int arabicNum) {
        if(arabicNum < 0){
            throw new SyntaxError("Roma dont have non-negative numbers!");
        }else if (arabicNum < 1) {
            return "0";
        } else {
            int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

            StringBuilder romanNum = new StringBuilder();

            for (int i = 0; i < arabicValues.length; i++) {
                while (arabicNum >= arabicValues[i]) {
                    romanNum.append(romanSymbols[i]);
                    arabicNum -= arabicValues[i];
                }
            }

            return romanNum.toString();
        }
    }

    public static String convertToArabic(String romanNum) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int end = romanNum.length() - 1;
        char[] arr = romanNum.toCharArray();
        int result = map.get(arr[end]);

        for (int i = end - 1; i >= 0; --i) {
            int num = map.get(arr[i]);
            if (num < map.get(arr[i + 1])) {
                result -= num;
            } else {
                result += num;
            }
        }
        return String.valueOf(result);
    }

    public static boolean isRoma(Character c) {

        Character[] arr = new Character[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};

        for (int i = 0; i <= arr.length - 1; ++i) {
            if (c == arr[i]) {
                return true;
            }
        }
        return false;
    }

    public static List<Lexeme> preparingRomaNum(List<Lexeme> lexemes) {
        for (Lexeme lexeme : lexemes) {
            if (lexeme.type == LexemeType.ROMA_NUM) {
                lexeme.value = Converter.convertToArabic(lexeme.value);
                lexeme.type = LexemeType.NUMBER;
            }
        }
        return lexemes;
    }

    public static boolean checkExpressionArabic(String expression) {
        boolean result = true;
        for (int i = 0; i < expression.length(); i++) {
            if (isRoma(expression.charAt(i))) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean checkExpressionRoma(String expression) {
        boolean result = true;
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))) {
                result = false;
                break;
            }
        }
        return result;
    }
}
