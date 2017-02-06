package com.concord.delegate;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.concord.service.UserService;

@Component
public class LoginDelegate {
	@Autowired
	UserService userService;

	/*
	 * public UserService getUserService() { return this.userService; }
	 * 
	 * public void setUserService(UserService userService) { this.userService =
	 * userService; }
	 */

	public boolean isValidUser(String username, String password) throws SQLException {
		return userService.isValidUser(username, password);
	}
}
