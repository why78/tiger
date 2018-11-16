/**
 *
 * Project Name:	javacore
 * File Name:	HmacTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年7月20日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tiger.research.javacore.crypto.HMAC;

/**
 * @author WangHuiyuan
 *
 */
public class HmacTest {


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String key = HMAC.init();
		System.out.println("Mac密钥:\n" + key);
		String word = "123";
		System.out.println(HMAC.encryptHMAC(word, key));
	}

}
