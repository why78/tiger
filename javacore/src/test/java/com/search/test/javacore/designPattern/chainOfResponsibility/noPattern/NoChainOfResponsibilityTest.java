package com.search.test.javacore.designPattern.chainOfResponsibility.noPattern;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/1/29
 * Version:		1.0
 * Remark：
 */
public class NoChainOfResponsibilityTest {

    @Test
    public void test() {
        int amount = 10000;
        Staff staff = new Staff("ZhangFei");
        if(!staff.approve(amount)) {
            Manager manager = new Manager("GuanYu");
            if(!manager.approve(amount)) {
                CFO cfo = new CFO("LiuBei");
                cfo.approve(amount);
            }
        }
    }
}