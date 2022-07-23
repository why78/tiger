/**
 *
 * Project Name:	javacore
 * File Name:	Truck.java
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
public class Truck implements IVehicle {


	@Override
	public void drive() {
		System.out.println("I'm driving a truck!");
		
	}
	
	@Override
	public IVehicle createInstance() {
		return new Truck();
	}

}
