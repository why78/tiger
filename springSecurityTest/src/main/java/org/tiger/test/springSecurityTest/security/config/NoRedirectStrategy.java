/**
 *
 * Project Name:	springSecurityTest
 * File Name:	NoRedirectStrategy.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年9月30日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.security.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.RedirectStrategy;

/**
 * @author WangHuiyuan
 *
 */
public class NoRedirectStrategy implements RedirectStrategy {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.RedirectStrategy#sendRedirect(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		System.out.println("hello");
		
		// TODO Auto-generated method stub
		
	}

}
