/**
 *
 * Project Name:	javacore
 * File Name:	TestGuava.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2020年7月5日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.common;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;

/**
 * @author WangHuiyuan
 *
 */
public class TestGuava {
	
	Logger logger = LoggerFactory.getLogger(TestGuava.class);

	@Test
	public void test() throws InterruptedException {
		Stopwatch stopwatch = Stopwatch.createStarted();
		Thread.sleep(1000);
		long cost = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		logger.info("The first stopwatch elapsed: [{}ms]", cost);
		Stopwatch stopwatch1 = stopwatch.stop();
//		stopwatch1.start();
		Thread.sleep(1000);
		cost = stopwatch1.elapsed(TimeUnit.MILLISECONDS);
		logger.info("The second stopwatch after stopping the first stopwatch elapsed [{}ms]", cost);
		
	}

}
