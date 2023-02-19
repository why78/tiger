package com.search.test.javacore.designPattern.chainOfResponsibility;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/2/19
 * Version:		1.0
 * Remark：
 */
public abstract class Approver {

    protected String name;

    protected Approver nextApprover;

    public Approver(String name) {
        this.name = name;
    }

    public abstract void approve(int amount);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Approver getNextApprover() {
        return nextApprover;
    }

    public Approver setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
        return this.nextApprover;
    }
}
