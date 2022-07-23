/**
 *
 * Project Name:	Java8Test
 * File Name:	BaseBean.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年1月5日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.Java8Test.spring4.configuration;

/**
 * @author WangHuiyuan
 *
 */
public class BaseBean {

	private String username;
    private String url;
    private String password;

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void sayHello() {
        System.out.println("TestBean sayHello...");
    }

    public String toString() {
        return "username:" + this.username + ", url:" + this.url + ", password:" + this.password;
    }

    public void start() {
        System.out.println("TestBean is initializing...");
    }

    public void cleanUp() {
        System.out.println("TestBean is destroying...");
    }
}
