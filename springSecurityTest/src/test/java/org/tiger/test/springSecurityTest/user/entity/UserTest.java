/**
 *
 * Project Name:	springSecurityTest
 * File Name:	UserTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年12月2日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.user.entity;


import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * @author WangHuiyuan
 *
 */
public class UserTest {

	@Test
	public void test() {
		User user = User.builder().id("123").username("why").password("hello").build();
		System.out.println(JSON.toJSONString(user));
	}

}
