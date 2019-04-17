/**
 *
 * Project Name:	springSecurityTest
 * File Name:	SampleAuthenticationManagerTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年1月3日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.auth.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author WangHuiyuan
 *
 */
public class SampleAuthenticationManagerTest {

	@Test
	public void test() throws IOException {
		AuthenticationManager am = new SampleAuthenticationManager();

		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	    while(true) {
		    System.out.println("Please enter your username:");
		    String name = in.readLine();
		    System.out.println("Please enter your password:");
		    String password = in.readLine();
		    try {
		        Authentication request = new UsernamePasswordAuthenticationToken(name, password);
		        Authentication result = am.authenticate(request);
		        SecurityContextHolder.getContext().setAuthentication(result);
		        break;
		    } catch(AuthenticationException e) {
		        System.out.println("Authentication failed: " + e.getMessage());
		    }
	    }
	    System.out.println("Successfully authenticated. Security context contains: " +
	            SecurityContextHolder.getContext().getAuthentication());
	}
	

}
