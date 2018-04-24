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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author WangHuiyuan
 *
 */
public class LoginLogoutTest {

	@Test
	public void testRegexMac(){
		
		Pattern pattern = Pattern.compile("^SM-C5000;([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2};13800000006$");
		Matcher matcher = pattern.matcher("SM-C5001;02:00:00:00:00:00;13800000006");
		boolean b= matcher.matches();
		System.out.println(b);
	}
	
	Subject subject;
	Factory<SecurityManager> factory;
	
	@Before
	public void init(){
//		factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
	}
	
	@Test
	public void isAuthenticated(){
		if(subject.isAuthenticated())
			System.out.println("Welcome zhang!!!");
		else
			System.out.println("Zhang is illeaged!!!");
		
	}
	
	@Test
	public void testLogin(){
		subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		try{
			subject.login(token);
		}catch(AuthenticationException ex){
			System.out.println("Zhang login occured errors !!!");
			ex.printStackTrace();
		}
		
		this.isAuthenticated();

		subject.logout();
		
		this.isAuthenticated();
	}
}
