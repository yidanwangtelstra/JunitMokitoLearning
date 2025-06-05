package com.linkedin.app;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b){
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    public boolean isEven(int i) {
        return i%2 == 0 ? true : false;
    }
}
