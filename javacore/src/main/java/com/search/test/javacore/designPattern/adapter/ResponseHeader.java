/**
 *
 * Project Name:	hbec-app-weisecurity-api-gateway-commons
 * File Name:	Header.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2017年9月8日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.adapter;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author WangHuiyuan
 *
 */
public class ResponseHeader implements Serializable {

	private static final long serialVersionUID = -7678690856293877358L;

	@JSONField(name="MSG_CODE")
	private String msg_code;
	
	@JSONField(name="MSG_TEXT")
	private String msg_text;
	
	@JSONField(name="SESSION")
	private String session;

	public ResponseHeader(){	}
	
	public ResponseHeader(String msg_code, String msg_text){
		this.msg_code = msg_code;
		this.msg_text = msg_text;
	}
	
	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getMsg_code() {
		return msg_code;
	}

	public void setMsg_code(String msg_code) {
		this.msg_code = msg_code;
	}

	public String getMsg_text() {
		return msg_text;
	}

	public void setMsg_text(String msg_text) {
		this.msg_text = msg_text;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ResponseHeader{");
		sb.append("msg_code='").append(msg_code).append('\'');
		sb.append(", msg_text='").append(msg_text).append('\'');
		sb.append(", session='").append(session).append('\'');
		sb.append('}');
		return sb.toString();
	}

	public static class ResponseHeaderBuilder{
		private String msg_code;
		private String msg_text;
		private String session;
		
		public ResponseHeaderBuilder(){
			
		}
		
		public static ResponseHeaderBuilder header(){
			return new ResponseHeaderBuilder();
		}
		
		public ResponseHeaderBuilder msg_code(String msg_code){
			this.msg_code = msg_code;
			return this;
		}
		
		public ResponseHeaderBuilder msg_text(String msg_text){
			this.msg_text = msg_text;
			return this;
		}
		
		public ResponseHeaderBuilder session(String session){
			this.session = session;
			return this;
		}
		
		public ResponseHeader build(){
			ResponseHeader header = new ResponseHeader(this.msg_code, this.msg_text);
			header.setSession(session);
			return header;
		}
	}
}
