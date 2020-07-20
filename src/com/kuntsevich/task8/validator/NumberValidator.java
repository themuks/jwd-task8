package com.kuntsevich.task8.validator;

public class NumberValidator {

    public boolean isNumberStringValid(String str) {
        return str.matches("-?\\d+");
    }
}
