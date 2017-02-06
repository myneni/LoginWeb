package com.concord.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.concord.dao.UserDao;

public class UserDaoImpl implements UserDao {
	DataSource dataSource;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean isValidUser(String username, String password) throws SQLException {

		String query = "select count(1) from user where username=? and password=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet resultset = pstmt.executeQuery();
		if (resultset.next())
			return (resultset.getInt(1) > 0);

		return false;
	}

}
