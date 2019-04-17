/**
 *
 * Project Name:	springSecurityTest
 * File Name:	UserAuthenticationService.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年9月30日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.auth.api;

import java.util.Optional;

import org.tiger.test.springSecurityTest.user.entity.User;

/**
 * @author WangHuiyuan
 *
 */
public interface UserAuthenticationService {

	/**
	 * Logs in with the given {@code username} and {@code password}.
	 *
	 * @param username
	 * @param password
	 * @return an {@link Optional} of a user when login succeeds
	 */
	Optional<String> login(String username, String password);

	/**
	 * Finds a user by its dao-key.
	 *
	 * @param token
	 *            user dao key
	 * @return
	 */
	Optional<User> findByToken(String token);

	/**
	 * Logs out the given input {@code user}.
	 *
	 * @param user
	 *            the user to logout
	 */
	void logout(User user);
}
