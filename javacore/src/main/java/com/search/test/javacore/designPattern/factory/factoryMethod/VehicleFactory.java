/**
 *
 * Project Name:	javacore
 * File Name:	VehicleFactory.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2021年4月18日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.factory.factoryMethod;

import com.search.test.javacore.designPattern.factory.factoryMethod.Vehicle.Color;
import com.search.test.javacore.designPattern.factory.factoryMethod.Vehicle.Size;

/**
 * @author WangHuiyuan
 *
 */
public abstract class VehicleFactory {

	public abstract Vehicle createVehicle(Size size);
	
	public Vehicle orderVehicle(Size size, Color color) {
		Vehicle one = createVehicle(size);
		one.setColor(color);
		one.testVehicle();
		return one;
	}
}
