package com.search.test.javacore.designPattern.chainOfResponsibility;

import org.junit.Test;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/2/19
 * Version:		1.0
 * Remark：
 */
public class ChainOfResponsibilityTest {

    @Test
    public void test() {
        Staff flightJohn = new Staff("ZhangFei");
        flightJohn.setNextApprover(new Manager("GuanYu"))
                .setNextApprover(new CFO("LiuBei"));

        flightJohn.approve(1000);

        flightJohn.approve(3000);

        flightJohn.approve(6000);

        flightJohn.approve(11000);
    }
}
