/**
 *
 * Project Name:	javacore
 * File Name:	VehicleFactory.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2021年4月3日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.factory.staticFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.search.test.javacore.designPattern.model.Car;
import com.search.test.javacore.designPattern.model.IVehicle;
import com.search.test.javacore.designPattern.model.Truck;

/**
 * @author WangHuiyuan
 *
 */
public class VehicleFactory {

	public enum VehicleType {
		Car, Truck
	}
	
	//////////////////////////////////////////////////////////////
	// 注册实例的静态工厂模式
	public IVehicle createVehicle(VehicleType type) throws Exception {
		return registeredProducts.get(type.name()).createInstance();
	}

	private Map<String, IVehicle> registeredProducts = new HashMap<>();
	
	public void registerVehicle(VehicleType type, IVehicle vehicle) {
		registeredProducts.put(type.name(), vehicle);
	}
	
	//////////////////////////////////////////////////////////////
	// 反射机制的静态工厂模式
	public static IVehicle getVehicle(VehicleType type) throws Exception {
		return getVehicle(type.name());
	}
	
	public static IVehicle getVehicle(String type) throws Exception {
		return (IVehicle) registeredProductClass.get(type).newInstance();
	}
	
	private static Map<String, Class<?>> registeredProductClass = new HashMap<>();
	
	public static void registerVehicleClass(VehicleType type, Class<?> vehicleClass) {
		registeredProductClass.put(type.name(), vehicleClass);
	}
	
	public static void registerVehicleClass(String type, Class<?> vehicleClass) {
		registeredProductClass.put(type, vehicleClass);
	}
	
	
	/**
	 * *********************************************
	* @Title: loadVehicleClass
	* @Description: 通过读取配置来注册
	* @throws IOException
	* @throws ClassNotFoundException
	 */
	public static void loadVehicleClass() throws IOException, ClassNotFoundException {
		Properties props = new Properties();
		InputStream in = VehicleFactory.class.getClassLoader().getResourceAsStream("bean.properties");
		props.load(in);
		
		Enumeration<Object> keys = props.keys();
		while (keys.hasMoreElements()){
			String key = keys.nextElement().toString();
            //通过类名拿到全类名（value）
            String beanPath = props.getProperty(key);
            VehicleType type = Enum.valueOf(VehicleType.class, key);
            Class<?> beanClass = Class.forName(beanPath);
            registerVehicleClass(type, beanClass);
		}
		System.out.println(String.format("Registered classes: %s", JSON.toJSONString(registeredProductClass.keySet())));
	}
	
	
	//////////////////////////////////////////////////////////////
	// 静态工厂模式，代码级别new创建，在新产品时需要修改代码，不合适
	public static IVehicle create(VehicleType type) {
		switch(type) {
		case Car:
			return new Car();
		case Truck:
			return new Truck();
		default:
			return null;
		}
	}
}
