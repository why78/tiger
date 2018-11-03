/**
 *
 * Project Name:	javacore
 * File Name:	TestJson.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年10月23日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.common;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * @author WangHuiyuan
 *
 */
public class TestJson {


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Map<String,String> main = new HashMap<>();
		Map<String,String> sub = new HashMap<>();
		sub.put("a1", "a1");
		main.put("name", "why");
		main.put("sub", JSON.toJSONString(sub));
		System.out.println(JSON.toJSONString(JSON.toJSONString(main)));
	}
	
	

}
