/**
 *
 * Project Name:	hbec-app-weisecurity-api-gateway-commons
 * File Name:	Response.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2017年9月8日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.adapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author WangHuiyuan
 *
 */
public final class Response implements Serializable {

	private static final long serialVersionUID = 5809734673717201154L;

	@JSONField(name="body")
	private Map<String,String> body = new HashMap<>();
	
	@JSONField(name="header")
	private ResponseHeader header;
	
	public Map<String, String> getBody() {
		return body;
	}

	public void setBody(Map<String, String> body) {
		this.body = body;
	}

	private Response(){
	}

	public ResponseHeader getHeader() {
		return header;
	}

	public void setHeader(ResponseHeader header) {
		this.header = header;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Response{");
		sb.append("datas=").append(body);
		sb.append(", header=").append(header);
		sb.append('}');
		return sb.toString();
	}
	
	public static Response headerOnly(String msgCode, String msgText){
		ResponseHeader header = ResponseHeader.ResponseHeaderBuilder.header()
				.msg_code(msgCode)
				.msg_text(msgText)
				.build();
		return Response.newBuilder().header(header).build();
	}
	
	public static Response headerOnly(String msgCode, String msgText, String session){
		ResponseHeader header = ResponseHeader.ResponseHeaderBuilder.header()
				.msg_code(msgCode)
				.msg_text(msgText)
				.session(session)
				.build();
		return Response.newBuilder().header(header).build();
	}
	
	public static ResponseBuilder newBuilder(){
		return new ResponseBuilder();
	}

	public static class ResponseBuilder{
		
		private ResponseHeader header;
		
		private Map<String,String> body = new HashMap<>();
		
		public ResponseBuilder(){	
		}
		
		public ResponseBuilder header(ResponseHeader header){
			this.header = header;
			return this;
		}
		
		public ResponseBuilder body(Map<String,String> body){
			this.body = body;
			return this;
		}
		
		public Response build(){
			Response response = new Response();
			response.header = this.header;
			response.body = this.body;
			return response;
		}
	}
}
