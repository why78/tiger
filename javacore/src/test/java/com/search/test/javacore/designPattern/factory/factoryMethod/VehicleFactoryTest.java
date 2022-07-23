/**
 *
 * Project Name:	javacore
 * File Name:	VehicleFactoryTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2021年4月18日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.factory.factoryMethod;

import org.junit.Test;

import com.search.test.javacore.designPattern.factory.factoryMethod.Vehicle.Color;
import com.search.test.javacore.designPattern.factory.factoryMethod.Vehicle.Size;

/**
 * @author WangHuiyuan
 *
 */
public class VehicleFactoryTest {

	/**
	 * Test method for {@link com.search.test.javacore.designPattern.factory.factoryMethod.VehicleFactory#orderVehicle(com.search.test.javacore.designPattern.factory.factoryMethod.Vehicle.Size, com.search.test.javacore.designPattern.factory.factoryMethod.Vehicle.Color)}.
	 */
	@Test
	public void testOrderVehicle() {
		VehicleFactory factory = new CarFactory();
		factory.orderVehicle(Size.Small, Color.green);
		
		VehicleFactory truckFactory = new TruckFactory();
		truckFactory.orderVehicle(Size.Small, Color.yellow);
	}
	
	public static class MountainBike extends Vehicle {};
	public static class CityBike extends Vehicle {};
	
	@Test
	public void testOrderBike() {
		VehicleFactory bikeFactory = new VehicleFactory() {
	
			@Override
			public Vehicle createVehicle(Size size) {
				switch(size) {
				case Small:
					return new CityBike();
				case Large:
					return new MountainBike();
				}
				return null;
			}
		};
		bikeFactory.orderVehicle(Size.Large, Color.red);
	}

}
