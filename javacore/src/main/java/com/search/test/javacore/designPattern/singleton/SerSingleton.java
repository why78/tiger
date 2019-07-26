/**
 *
 * Project Name:	javacore
 * File Name:	SerSingleton.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年7月26日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.singleton;

import java.io.Serializable;

/**
 * @author WangHuiyuan
 *
 */
public class SerSingleton implements Serializable {

	private static final long serialVersionUID = -5682669388602344600L;
	
	private volatile static SerSingleton uniqueInstance;

	private SerSingleton() {
	}

	public static SerSingleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (SerSingleton.class) {
				if (uniqueInstance == null) {// 进入区域后，再检查一次，如果仍是null,才创建实例
					uniqueInstance = new SerSingleton();
				}
			}
		}
		return uniqueInstance;
	}
	
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
