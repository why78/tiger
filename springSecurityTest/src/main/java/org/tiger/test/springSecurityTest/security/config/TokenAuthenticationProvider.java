/**
 *
 * Project Name:	springSecurityTest
 * File Name:	TokenAuthenticationProvider.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年9月30日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.security.config;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.tiger.test.springSecurityTest.auth.api.UserAuthenticationService;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

/**
 * @author WangHuiyuan
 *
 */
@Component
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@NonNull
	UserAuthenticationService auth;

	@Override
	protected void additionalAuthenticationChecks(final UserDetails d, final UsernamePasswordAuthenticationToken auth) {
		// Nothing to do
	}

	@Override
	protected UserDetails retrieveUser(final String username,
			final UsernamePasswordAuthenticationToken authentication) {
		final Object token = authentication.getCredentials();
		return Optional.ofNullable(token).map(String::valueOf).flatMap(auth::findByToken).orElseThrow(
				() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
	}
}
