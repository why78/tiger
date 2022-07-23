/**
 *
 * Project Name:	javacore
 * File Name:	TruckFactory.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2021年4月18日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.factory.factoryMethod;

import com.search.test.javacore.designPattern.factory.factoryMethod.Vehicle.Size;

/**
 * @author WangHuiyuan
 *
 */
public class TruckFactory extends VehicleFactory {


	@Override
	public Vehicle createVehicle(Size size) {
		switch(size) {
		case Small: 
			return new SmallTruck();
		case Large:
			return new LargeTruck();
		}
		return null;
	}
	
	public class SmallTruck extends Vehicle {

		@Override
		public void testVehicle() {
			System.out.println("I'm a small truck!");
			
		}
		
	}

	public class LargeTruck extends Vehicle {

//		@Override
//		public void testVehicle() {
//			System.out.println("I'm a large truck!");
//			
//		}
		
	}
}
