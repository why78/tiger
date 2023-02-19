package com.search.test.javacore.designPattern.chainOfResponsibility;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/2/19
 * Version:		1.0
 * Remark：
 */
public class Manager extends Approver {

    public Manager(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if(amount <= 5000) {
            System.out.println(String.format("Approved by manager[%s]", name));
        } else {
            System.out.println(String.format("Manager[%s] has not approval authority[%d], the leader[%s] will approve it.",
                    name, amount, this.nextApprover.name));
            this.nextApprover.approve(amount);
        }
    }
}
