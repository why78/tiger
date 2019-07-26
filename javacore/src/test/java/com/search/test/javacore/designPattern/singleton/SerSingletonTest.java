/**
 *
 * Project Name:	javacore
 * File Name:	SerSingletonTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年7月26日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * @author WangHuiyuan
 *
 */
public class SerSingletonTest {

	@Test
	public void test() throws Exception {
		String fileName = "SerSingleton.obj";
		
		SerSingleton s1 = SerSingleton.getInstance();
		s1.setContent("Singleton serialization!");
		System.out.println("The content before serializatin:" + s1.getContent());
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(s1);
		oos.flush();
		oos.close();
		
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		SerSingleton s2 = (SerSingleton)ois.readObject();
		ois.close();
		
		System.out.println("s1:" + s1);
		System.out.println("s2:" + s2);
		System.out.println("The content after serialization:" + s2.getContent());
		System.out.println("Validation s1 and s2, after serialization: " + (s1==s2));
		
	}

}
