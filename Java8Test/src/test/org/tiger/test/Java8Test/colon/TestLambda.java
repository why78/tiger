/**
 *
 * Project Name:	Java8Test
 * File Name:	TestLambda.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年12月31日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.Java8Test.colon;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WangHuiyuan
 *
 */
public class TestLambda {

	Logger logger = LoggerFactory.getLogger(TestLambda.class);
	
	interface MathOperation {
		int operation(int a, int b);
//		int operation1(int a, int b, int c);
	}
	
	@Test
	public void test() {
		MathOperation addition = (int a, int b) -> a + b;
		MathOperation subtraction = (a, b) -> a - b;
		MathOperation multipication = (a, b) -> {
			return a * b;
		};
		MathOperation division = (a, b) -> a / b;
		
		int a = 10;
		int b = 5;
		logger.info("{} + {} = {}", a, b, addition.operation(a, b));
		logger.info("{} - {} = {}", a, b, subtraction.operation(a, b));
		logger.info("{} * {} = {}", a, b, multipication.operation(a, b));
		logger.info("{} / {} = {}", a, b, division.operation(a, b));
	}

}
