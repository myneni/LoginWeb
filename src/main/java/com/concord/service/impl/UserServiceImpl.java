package com.concord.service.impl;

import java.sql.SQLException;

import com.concord.dao.UserDao;
import com.concord.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserDao getUserDao() {
		return this.userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean isValidUser(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.isValidUser(username, password);
	}

}
