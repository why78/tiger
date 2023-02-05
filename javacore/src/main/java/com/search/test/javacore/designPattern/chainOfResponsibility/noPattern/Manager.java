package com.search.test.javacore.designPattern.chainOfResponsibility.noPattern;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/1/29
 * Version:		1.0
 * Remark：
 */
public class Manager {

    private String name;

    public Manager(String name) {
        this.name = name;
    }

    public boolean approve(int amount){
        if(amount <= 5000) {
            System.out.println(String.format("Approved by manager[%s]", name));
            return true;
        } else {
            System.out.println(String.format("No approval authority, please contact the leader of manager[%s]", name));
            return false;
        }
    }
}
