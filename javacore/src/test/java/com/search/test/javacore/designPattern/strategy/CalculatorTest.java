package com.search.test.javacore.designPattern.strategy;

import org.junit.Test;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/1/29
 * Version:		1.0
 * Remark：
 */
public class CalculatorTest {

    @Test
    public void calculator() {
        int a = 6;
        int b = 3;
        Calculator calculator = new Calculator();
        calculator.setStrategy(new Addition());
        int result = calculator.calculate(a, b);
        System.out.println(String.format("Add %d to %d is %d", b, a, result));

        calculator.setStrategy(new Subtraction());
        result = calculator.calculate(a, b);
        System.out.println(String.format("%d subtract from %d is %d", b, a, result));
    }
}