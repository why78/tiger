package com.search.test.javacore.designPattern.strategy;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/1/29
 * Version:		1.0
 * Remark：
 */
public class Calculator {

    public int calculate(int a, int b) {
        return this.strategy.calculate(a,b);
    }

    public IStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    private IStrategy strategy;

}
