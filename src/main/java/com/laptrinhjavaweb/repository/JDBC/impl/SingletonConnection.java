package com.laptrinhjavaweb.repository.JDBC.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	private static SingletonConnection instance = null;
	private Connection connection = null;
	private static final String DRIVER_JDBC = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/javaweb62020module1";
	private static final String USER = "root";
	private static final String PASSWORK = "saker0905971230";
	
	private SingletonConnection() {
		try {
			Class.forName(DRIVER_JDBC);
			connection = DriverManager.getConnection(URL, USER, PASSWORK);
		} catch (ClassNotFoundException | SQLException e) {
			e.getMessage();
		}
	}
	
	public static SingletonConnection getInstance() {
		try {
			if (instance == null || instance.getConnection().isClosed()) {
				instance = new SingletonConnection();
				return instance;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
