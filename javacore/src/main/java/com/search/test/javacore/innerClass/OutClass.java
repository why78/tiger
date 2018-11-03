/**
 *
 * Project Name:	javacore
 * File Name:	OutClass.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年9月19日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.innerClass;

/**
 * @author WangHuiyuan
 *
 */
public class OutClass {

	private String b = "why";
	
	
	public class InnerClass{
		private String a;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}
		
		public String getOutProperty(){
			return b;
		}
	}
	
	
}
