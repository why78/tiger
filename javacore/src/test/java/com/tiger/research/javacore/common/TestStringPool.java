/**
 *
 * Project Name:	javacore
 * File Name:	TestStringPool.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2020年5月2日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.common;

import org.junit.Test;

/**
 * @author WangHuiyuan
 *
 */
public class TestStringPool {

	@Test
	public void test1() {
		
		String s1 = "hello";
		String s2 = "hello";
		String s3 = "Hel" + "lo";
		String s4 = "He" + new String("llo");
		String s5 = new String("Hello");
		String s6 = s5.intern();
		String s7 = "H";
		String s8 = "ello";
		String s9 = s7 + s8;
		System.out.println("s1 == s2: " + s1 == s2); // true
		System.out.println("s1 == s3: " + s1 == s3);  // true
		System.out.println("s1 == s4: " + s1 == s4);  // false
		System.out.println("s1 == s9: " + s1 == s9);  // false
		System.out.println("s4 == s5: " + s4 == s5);  // false
		System.out.println("s1 == s6: " + s1 == s6);  // true
		"hello".intern();
	}
	
	public static final String A = "ab"; // 常量A
	public static final String B = "cd"; // 常量B
	
	@Test
	public void test2() {
	     String s = A + B;  // 将两个常量用+连接对s进行初始化 
	     String t = "abcd";   
	    if (s == t) {   
	         System.out.println("s等于t，它们是同一个对象");   
	     } else {   
	         System.out.println("s不等于t，它们不是同一个对象");   
	     }   
	 } 
	

/*	public static void main(String[] args) {
		//保持引用，防止自动垃圾回收
		List<String> list = new ArrayList<String>();
		        
		int i = 0;
		        
		while(true){
		    //通过intern方法向常量池中手动添加常量
		    list.add(String.valueOf(i++).intern());
		}
	}*/

}
