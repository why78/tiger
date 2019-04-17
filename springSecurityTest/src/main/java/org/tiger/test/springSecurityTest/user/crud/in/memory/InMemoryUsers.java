/**
 *
 * Project Name:	springSecurityTest
 * File Name:	InMemoryUsers.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年9月30日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.user.crud.in.memory;

import static java.util.Optional.ofNullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.tiger.test.springSecurityTest.user.crud.api.UserCrudService;
import org.tiger.test.springSecurityTest.user.entity.User;

/**
 * @author WangHuiyuan
 *
 */
@Service
public class InMemoryUsers implements UserCrudService {

	Map<String, User> users = new HashMap<>();

	@Override
	public User save(final User user) {
		return users.put(user.getId(), user);
	}

	@Override
	public Optional<User> find(final String id) {
		return ofNullable(users.get(id));
	}

	@Override
	public Optional<User> findByUsername(final String username) {
		return users.values().stream().filter(u -> Objects.equals(username, u.getUsername())).findFirst();
	}
}
