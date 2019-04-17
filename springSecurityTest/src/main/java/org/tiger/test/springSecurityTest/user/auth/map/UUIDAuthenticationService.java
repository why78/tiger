/**
 *
 * Project Name:	springSecurityTest
 * File Name:	UUIDAuthenticationService.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年10月11日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.user.auth.map;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.tiger.test.springSecurityTest.auth.api.UserAuthenticationService;
import org.tiger.test.springSecurityTest.user.crud.api.UserCrudService;
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
@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UUIDAuthenticationService implements UserAuthenticationService {

	@NonNull
	UserCrudService users;

	@Override
	public Optional<String> login(final String username, final String password) {
		final String uuid = UUID.randomUUID().toString();
		final User user = User.builder().id(uuid).username(username).password(password).build();

		users.save(user);
		return Optional.of(uuid);
	}

	@Override
	public Optional<User> findByToken(final String token) {
		return users.find(token);
	}

	@Override
	public void logout(final User user) {

	}
}
