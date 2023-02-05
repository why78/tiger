package com.search.test.javacore.designPattern.chainOfResponsibility.noPattern;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/1/29
 * Version:		1.0
 * Remark：
 */
public class CFO {

    private String name;

    public CFO(String name) {
        this.name = name;
    }

    public boolean approve(int amount){
        if(amount <= 10000) {
            System.out.println(String.format("Approved by CFO[%s]", name));
            return true;
        } else {
            System.out.println(String.format("Rejection application, CFO[%s]", name));
            return false;
        }
    }
}
