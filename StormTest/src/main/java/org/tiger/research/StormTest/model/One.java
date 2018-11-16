/**
 *
 * Project Name:	StormTest
 * File Name:	One.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月1日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.model;

import java.io.Serializable;

/**
 * @author WangHuiyuan
 *
 */
public class One implements Serializable {

	private static final long serialVersionUID = -8575793061824988480L;
	
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
