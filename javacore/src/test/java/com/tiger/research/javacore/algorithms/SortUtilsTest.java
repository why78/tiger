/**
 *
 * Project Name:	javacore
 * File Name:	SortUtilsTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年10月7日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.algorithms;


import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WangHuiyuan
 *
 */
public class SortUtilsTest {
	
	private final static Logger logger = LoggerFactory.getLogger(SortUtilsTest.class);

	int[] source = {49,38,65,97,76,13,27,49};
	
	@Before
	public void init() {
		logger.info("source: {}", source);
	}

	/**
	 * Test method for {@link com.tiger.research.javacore.algorithms.SortUtils#bubbleSort(int[])}.
	 */
	@Test
	public void testBubbleSort() {
		int[] dest = SortUtils.bubbleSort(source);
		logger.info("The final result: {}",dest);
	}
	
	@Test
	public void testQuickSort() {
		
		SortUtils.quickSort(source, 0, source.length-1);
		logger.info("The final result: {}",source);
	}

}
