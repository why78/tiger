/**
 *
 * Project Name:	Java8Test
 * File Name:	TestColon.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年12月28日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.Java8Test.colon;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author WangHuiyuan
 *
 */
public class TestColon extends TestCase {
	

	List<String> al = Arrays.asList("a","b","c","d");
	
	@Before
	public void init() {
		
	}

	@Test
	public void test1() {
        for (String a: al) {
            AcceptMethod.printValue(a);
        }
        System.out.println("=======");
        //下面的for each循环和上面的循环是等价的 
        al.forEach((x)->{
            AcceptMethod.printValue(x);
        });
        System.out.println("=======");
        al.forEach(x -> {
        	AcceptMethod.printValue(x);
        	}
        );
	}
	
	@Test
	public void test2() {
		Consumer<String> methodParam = AcceptMethod::printValue;
		al.forEach(methodParam);
		
		System.out.println("======");
		al.forEach(x -> methodParam.accept(x));
	}
}
