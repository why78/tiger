/**
 *
 * Project Name:	javacore
 * File Name:	Car.java
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
public class CarFactory extends VehicleFactory {
	
	@Override
	public Vehicle createVehicle(Size size) {
		
		switch(size) {
		case Small: 
			return new SportCar();
		case Large:
			return new SedanCar();
		}
		return null;
	}

		
	public class SportCar extends Vehicle {
		
	}
	
	public class SedanCar extends Vehicle {

		@Override
		public void testVehicle() {
			System.out.println(String.format("I'm a sedan car! I'm %s!", this.color.name()));
			
		}
		
	}

}
