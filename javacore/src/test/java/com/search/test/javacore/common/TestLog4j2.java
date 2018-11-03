/**
 *
 * Project Name:	javacore
 * File Name:	TestLog4j2.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年10月26日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.common;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WangHuiyuan
 *
 */
public class TestLog4j2 {

	private final static Logger logger = LoggerFactory.getLogger(TestLog4j2.class);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBurstFilter() throws InterruptedException {
//		int count = 0;
//		for(int j=0; j<30; j++){
//			int i = count + 10;
//			while(count < i){
//				logger.info("{}", count);
//				count++;
//			}
//			Thread.sleep(100);
//		}
		
		for(int i=1; i<=100; i++){
			int begin = (i-1)*10;
			int end = i*10;
			for(int j=begin; j<end; j++){
				logger.info("{}", j);
			}
		}
	}

}
