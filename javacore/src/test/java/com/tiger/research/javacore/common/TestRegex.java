package com.tiger.research.javacore.common;

import org.junit.Test;

public class TestRegex {

	@Test
	public void testReplace() {
		String str = "toe-stock,";
		System.out.println(str);
		System.out.println(str.replaceAll("(\\S*)(,)", "$1"));

		String str1 = "012345678231206546123";

		System.out.println(str1.replaceAll("(123)(45678)", "$1.jsp"));
	}

}
