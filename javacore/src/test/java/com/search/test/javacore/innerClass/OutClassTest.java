/**
 *
 * Project Name:	javacore
 * File Name:	OutClassTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年9月19日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.innerClass;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.search.test.javacore.innerClass.OutClass.InnerClass;

/**
 * @author WangHuiyuan
 *
 */
public class OutClassTest {


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		OutClass outClass = new OutClass();
		OutClass.InnerClass innerClass = outClass.new InnerClass();
		innerClass.setA("hello");
		System.out.println(innerClass.getA());
		System.out.println(innerClass.getOutProperty());
	}

}
