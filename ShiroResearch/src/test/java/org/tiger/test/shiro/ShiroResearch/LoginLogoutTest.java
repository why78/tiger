/**
 *
 * Project Name:	ShiroResearch
 * File Name:	LoginLogoutTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年4月17日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.shiro.ShiroResearch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Test;

/**
 * @author WangHuiyuan
 *
 */
public class LoginLogoutTest {

	@Test
	public void testRegexMac(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		
		Pattern pattern = Pattern.compile("^SM-C5000;([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2};13800000006$");
		Matcher matcher = pattern.matcher("SM-C5001;02:00:00:00:00:00;13800000006");
		boolean b= matcher.matches();
		System.out.println(b);
	}
	
	
}
