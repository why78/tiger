/**
 *
 * Project Name:	Java8Test
 * File Name:	MyImportSelector.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年1月5日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.Java8Test.spring4.configuration;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author WangHuiyuan
 *
 */
public class MyImportSelector implements ImportSelector {


	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		
		return new String[] {"org.tiger.test.Java8Test.spring4.configuration.Triangle"};
	}

}
