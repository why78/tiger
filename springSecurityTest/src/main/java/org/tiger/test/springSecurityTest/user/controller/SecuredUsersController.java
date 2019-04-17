/**
 *
 * Project Name:	springSecurityTest
 * File Name:	SecuredUsersController.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年10月11日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tiger.test.springSecurityTest.auth.api.UserAuthenticationService;
import org.tiger.test.springSecurityTest.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

/**
 * @author WangHuiyuan
 *
 */
@RestController
@RequestMapping("/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class SecuredUsersController {

	@NonNull
	UserAuthenticationService authentication;

	@GetMapping("/current")
	User getCurrent(@AuthenticationPrincipal final User user) {
		return user;
	}

	@GetMapping("/logout")
	boolean logout(@AuthenticationPrincipal final User user) {
		authentication.logout(user);
		return true;
	}
}
