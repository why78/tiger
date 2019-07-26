/**
 *
 * Project Name:	javacore
 * File Name:	EnumSingleton.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年7月26日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.singleton;

/**
 * @author WangHuiyuan
 *
 */
public enum EnumSingleton {

	instance;
	
	public EnumSingleton getInstance() {
		return instance;
	}
}
