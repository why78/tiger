/**
 *
 * Project Name:	javacore
 * File Name:	Callback.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2022年7月31日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.callback;

/**
 * @author WangHuiyuan
 *
 */
@FunctionalInterface
public interface Callback {

	void execute() throws Exception;
}
