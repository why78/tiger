/**
 *
 * Project Name:	springSecurityTest
 * File Name:	User.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年9月30日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.user.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

import static java.util.Objects.requireNonNull;

/**
 * @author WangHuiyuan
 *
 */
@Value
@Builder
public class User implements UserDetails {

	private static final long serialVersionUID = -88047954743771082L;
	
	String id;

	String username;
	String password;

	@JsonCreator
	User(@JsonProperty("id") final String id, 
			@JsonProperty("username") final String username,
			@JsonProperty("password") final String password) {
		super();
		this.id = requireNonNull(id);
		this.username = requireNonNull(username);
		this.password = requireNonNull(password);
	}

	@JsonIgnore
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public String getId() {
		return id;
	}
}
