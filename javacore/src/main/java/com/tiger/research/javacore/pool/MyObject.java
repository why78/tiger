/**
 *
 * Project Name:	javacore
 * File Name:	MyObject.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年10月28日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.pool;

import java.util.Random;

/**
 * @author WangHuiyuan
 *
 */
public class MyObject {

	int id;
	boolean broken = false;
	
	public boolean isBroken() {
		return broken;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}

	public MyObject() {
		Random r = new Random();
		id = r.nextInt(1000);
	}
	
	public int getId() {
		return id;
	}
}
