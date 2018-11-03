/**
 *
 * Project Name:	javacore
 * File Name:	IAuthClient.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年9月19日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.adapter;



/**
 * @author WangHuiyuan
 *
 */
public interface IAuthClient {

	public Response validationTicket(String ticket);
	
	public Response validateTicketAndResource(String ticket, String resource);
	
	public static class AuthClientNoOp implements IAuthClient{
		
		private static Response ok = Response.headerOnly("200", "OK");

		@Override
		public Response validationTicket(String ticket) {
			
			return ok;
		}

		@Override
		public Response validateTicketAndResource(String ticket, String resource) {
			
			return ok;
		}
		
	}
}
