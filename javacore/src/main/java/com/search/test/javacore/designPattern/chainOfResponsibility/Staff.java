package com.search.test.javacore.designPattern.chainOfResponsibility;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/1/29
 * Version:		1.0
 * Remark：
 */
public class Staff extends Approver {

    public Staff(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if(amount <= 1000) {
            System.out.println(String.format("Approved by staff[%s]", name));
        } else {
            System.out.println(String.format("Staff[%s] has not approval authority[%d], the leader[%s] will approve.",
                    this.name, amount, this.getNextApprover().name));
            this.getNextApprover().approve(amount);
        }
    }
}
