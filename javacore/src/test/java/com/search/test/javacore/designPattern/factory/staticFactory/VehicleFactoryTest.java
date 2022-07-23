/**
 *
 * Project Name:	javacore
 * File Name:	VehicleFactoryTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2021年4月17日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.factory.staticFactory;

import java.io.IOException;

import org.junit.Test;

import com.search.test.javacore.designPattern.factory.staticFactory.VehicleFactory;
import com.search.test.javacore.designPattern.factory.staticFactory.VehicleFactory.VehicleType;
import com.search.test.javacore.designPattern.model.Car;
import com.search.test.javacore.designPattern.model.IVehicle;
import com.search.test.javacore.designPattern.model.Truck;

/**
 * @author WangHuiyuan
 *
 */
public class VehicleFactoryTest {

	@Test
	public void testInstanceFactory() throws Exception {
		VehicleFactory factory = new VehicleFactory();
		factory.registerVehicle(VehicleType.Car, new Car());
		factory.registerVehicle(VehicleType.Truck, new Truck());
		
		IVehicle car = factory.createVehicle(VehicleType.Car);
		car.drive();
		
		IVehicle truck = factory.createVehicle(VehicleType.Truck);
		truck.drive();
	}
	
	@Test
	public void testReflectFactory() throws Exception {
		VehicleFactory.registerVehicleClass(VehicleType.Car, Car.class);
		VehicleFactory.registerVehicleClass(VehicleType.Truck, Truck.class);
		
		IVehicle car = VehicleFactory.getVehicle(VehicleType.Car);
		car.drive();
		
		IVehicle truck = VehicleFactory.getVehicle(VehicleType.Truck);
		truck.drive();
	}

	@Test
	public void testReflectFactoryByProperties() throws Exception {
		VehicleFactory.loadVehicleClass();
		
		IVehicle one = VehicleFactory.getVehicle("Car");
		one.drive();
		
		one = VehicleFactory.getVehicle("Truck");
		one.drive();
	}
}
