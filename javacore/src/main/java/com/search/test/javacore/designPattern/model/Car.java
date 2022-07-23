/**
 *
 * Project Name:	javacore
 * File Name:	Car.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2021年4月3日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.model;

/**
 * @author WangHuiyuan
 *
 */
public class Car implements IVehicle {


	@Override
	public void drive() {
		System.out.println("I'm driving a car!");
		
	}

	@Override
	public IVehicle createInstance() {
		return new Car();
	}

	
}
