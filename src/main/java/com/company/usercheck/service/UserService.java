package com.company.usercheck.service;

import java.util.List;

import com.company.usercheck.domain.Result;
import com.company.usercheck.domain.User;

/**
 * Interface for UserService Bean
 * @author hugo
 *
 */
public interface UserService {
	
	List<User> loadAll();
	void save(User user);
	
	Result<Boolean, List<String>> checkUserName(String userName) throws Exception;
}
