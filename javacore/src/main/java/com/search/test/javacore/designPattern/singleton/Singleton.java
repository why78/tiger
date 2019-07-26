/**
 *
 * Project Name:	javacore
 * File Name:	Singleton.java
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
public class Singleton {

	private volatile static Singleton uniqueInstance;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (Singleton.class) {
				if (uniqueInstance == null) {// 进入区域后，再检查一次，如果仍是null,才创建实例
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}
