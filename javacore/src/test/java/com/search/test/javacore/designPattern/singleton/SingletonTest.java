/**
 *
 * Project Name:	javacore
 * File Name:	SingletonTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年7月26日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

/**
 * @author WangHuiyuan
 *
 */
public class SingletonTest {

	@Test
	public void test() throws Exception {
		Singleton s1 = Singleton.getInstance();
		
		Singleton s2 = Singleton.getInstance();
		
		Constructor<Singleton> cs = Singleton.class.getDeclaredConstructor();
		cs.setAccessible(true);
		Singleton s3 = cs.newInstance();
		
		System.out.println("Validation s1 == s2: " + (s1==s2));
		System.out.println("Validation s1 == s3: " + (s1==s3));
		
		
	}

}
