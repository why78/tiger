package com.search.test.javacore.designPattern.strategy;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/1/28
 * Version:		1.0
 * Remark：
 */
public class Addition implements IStrategy{

    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
