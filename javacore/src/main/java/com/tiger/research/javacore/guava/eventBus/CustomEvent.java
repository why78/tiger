package com.tiger.research.javacore.guava.eventBus;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/2/5
 * Version:		1.0
 * Remark：
 */
public class CustomEvent {
    private int age;
    public CustomEvent(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }
}
