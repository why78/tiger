/**
 *
 * Project Name:	javacore
 * File Name:	Stock.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年2月1日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.jvm.memLeak;

/**
 * @author WangHuiyuan
 *
 */
public class Stock {

	private String code;
	private Double price;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
