/**
 *
 * Project Name:	Java8Test
 * File Name:	TestConfiguration.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年1月5日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.Java8Test.spring4.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * @author WangHuiyuan
 *
 */
@Configuration
@ComponentScan("org.tiger.test.Java8Test.spring4.configuration")
@Import({Circle.class, Square.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class TestConfiguration {

	private final static Logger logger = LoggerFactory.getLogger(TestConfiguration.class);
	
	public TestConfiguration() {
		logger.info("The TestConfiguration is starting!");
	}
	
	@Bean
	@Scope("prototype")
	public TestBean testBean() {
		return new TestBean();
	}
}
