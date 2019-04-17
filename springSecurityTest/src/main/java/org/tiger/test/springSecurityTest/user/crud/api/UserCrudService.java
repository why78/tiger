/**
 *
 * Project Name:	springSecurityTest
 * File Name:	UserCrudService.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年9月30日
 * Version:		1.0
 * Remark：
 */
package org.tiger.test.springSecurityTest.user.crud.api;

import java.util.Optional;

import org.tiger.test.springSecurityTest.user.entity.User;

/**
 * @author WangHuiyuan
 *
 */
public interface UserCrudService {

	User save(User user);

	Optional<User> find(String id);

	Optional<User> findByUsername(String username);
}
