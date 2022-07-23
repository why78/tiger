/**
 *
 * Project Name:	Java8Test
 * File Name:	MyImportBeanDefinitionRegistrar.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年1月5日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.Java8Test.spring4.configuration;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author WangHuiyuan
 *
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Rectangle.class);
		registry.registerBeanDefinition("rectangle", rootBeanDefinition);
		
	}

}
