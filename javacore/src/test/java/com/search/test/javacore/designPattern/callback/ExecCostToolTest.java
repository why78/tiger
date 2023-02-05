/**
 *
 * Project Name:	javacore
 * File Name:	ExecCostToolTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2022年7月31日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.callback;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author WangHuiyuan
 *
 */
public class ExecCostToolTest {


	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testCall() {
		ExecCostTool tool = new ExecCostTool();
		tool.call(() -> Thread.sleep(1000));
	}

}
