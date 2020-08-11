package com.laptrinhjavaweb.repository.JDBC.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.Mapper.RowMapper;
import com.laptrinhjavaweb.repository.JDBC.GenericRepo;

public class GenericRepoIMPL<T> implements GenericRepo<T>{
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = ("jdbc:mysql://localhost:3306/javaweb62020module1");
			String user = "root";
			String password = "saker0905971230";
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public long save(String sql) {
	
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		long id = -1;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			while (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
			return -1;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public T findById(String sql, long id, RowMapper<T> rowMapper) {
		T t = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				t = rowMapper.maprow(resultSet);
			}
			return t;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

}
