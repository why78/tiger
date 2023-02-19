package com.search.test.javacore.designPattern.chainOfResponsibility;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/2/19
 * Version:		1.0
 * Remark：
 */
public class CFO extends Approver {

    public CFO(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if(amount <= 10000) {
            System.out.println(String.format("Approved by CFO[%s]", name));
        } else {
            System.out.println(String.format("Rejection application, CFO[%s]", name));
        }
    }
}
