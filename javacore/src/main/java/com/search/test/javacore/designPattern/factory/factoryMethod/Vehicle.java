/**
 *
 * Project Name:	javacore
 * File Name:	Vehicle.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2021年4月18日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.factory.factoryMethod;

/**
 * @author WangHuiyuan
 *
 */
public abstract class Vehicle {

	public enum Color {
		red, yellow, green
	}
	
	Color color;
	
	public enum Size {
		Small, Large
	}
	
	String name;
	
	public Vehicle() {
		this.name = this.getClass().getSimpleName();
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void testVehicle() {
		System.out.println(String.format("I'm a %s! I'm %s!", this.name, this.color.name()));
	}
	
}


