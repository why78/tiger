/**
 *
 * Project Name:	javacore
 * File Name:	TestThread.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年3月12日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.Thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WangHuiyuan
 *
 */
public class MyThread extends Thread {

	private static final AtomicInteger count = new AtomicInteger();
	
	@Override
	public void run() {
		System.out.println(count.incrementAndGet());
		while(true) {
			try {
				Thread.sleep(Integer.MAX_VALUE);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i=1; i<=200; i++) {
			(new MyThread()).start();
		}
		try {
			Thread.sleep(3600000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}
