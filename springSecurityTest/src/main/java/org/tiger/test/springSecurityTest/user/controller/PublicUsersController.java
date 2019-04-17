/**
 *
 * Project Name:	springSecurityTest
 * File Name:	PublicUsersController.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年10月1日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.user.controller;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tiger.test.springSecurityTest.auth.api.UserAuthenticationService;
import org.tiger.test.springSecurityTest.user.crud.api.UserCrudService;
import org.tiger.test.springSecurityTest.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * @author WangHuiyuan
 *
 */
@RestController
@RequestMapping("/public/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class PublicUsersController {

	@NonNull
	UserAuthenticationService authentication;
	@NonNull
	UserCrudService users;

	@PostMapping("/register")
	String register(@RequestParam("username") final String username, @RequestParam("password") final String password) {
		users.save(
				User.builder()
				.id(username)
				.username(username)
				.password(password).build());

		return login(username, password);
	}

	@PostMapping("/login")
	String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
		return authentication.login(username, password)
				.orElseThrow(() -> new RuntimeException("invalid login and/or password"));
	}
}
