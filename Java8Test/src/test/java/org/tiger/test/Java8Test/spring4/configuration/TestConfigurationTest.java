/**
 *
 * Project Name:	Java8Test
 * File Name:	TestConfigurationTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年1月5日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.Java8Test.spring4.configuration;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author WangHuiyuan
 *
 */
public class TestConfigurationTest {
	
	Logger logger = LoggerFactory.getLogger(TestConfigurationTest.class);

	ApplicationContext context;
	
	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(TestConfiguration.class);
		String[] beanNames = context.getBeanDefinitionNames();
		for(int i=0;i<beanNames.length;i++){
		    logger.info("bean名称为==={}", beanNames[i]);
		}
	}
	
	@Test
	public void test() {
		logger.info("Started!");
		TestBean bean = (TestBean) context.getBean("testBean");
		bean.setUsername("whx");
		bean.setUrl("www.whx.com");
		bean.setPassword("why");
		logger.info(bean.toString());
		
		TestBean1 bean1 = (TestBean1) context.getBean("testBean1");
		bean1.sayHello();
	}

}
