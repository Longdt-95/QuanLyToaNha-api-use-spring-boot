package com.laptrinhjavaweb.repository.JDBC.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.laptrinhjavaweb.Mapper.ObjectMapper;
import com.laptrinhjavaweb.Mapper.RowMapper;
import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.repository.JDBC.SimpleJpaRepository;

public class SimpleJpaRepositoryIMPL<T> implements SimpleJpaRepository<T> {

	private Class<T> getZClass() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) t;
		@SuppressWarnings("unchecked")
		Class<T> zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		return zClass;
	}

	@SuppressWarnings("static-access")
	@Override
	public long save(Object object) {
		String sql = buildSqlInsert();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		long id = -1;
		try {
			connection = SingletonConnection.getInstance().getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
			int index = 1;
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (!field.getName().equals("id")) {
					statement.setObject(index, field.get(object));
					index++;
				}
			}
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			while (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException | IllegalAccessException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
			return id;
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

	}

	private String buildSqlInsert() {
		Class<T> zClass = getZClass();
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		Field[] fields = zClass.getDeclaredFields();
		StringBuilder name = new StringBuilder();
		StringBuilder parameter = new StringBuilder();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class)) {
				if (!field.getName().equals("id")) {
					Column column = field.getAnnotation(Column.class);
					name.append(column.name() + ",");
					parameter.append("?,");
				}
			}
		}
		name.deleteCharAt(name.toString().length() - 1);
		parameter.deleteCharAt(parameter.toString().length() - 1);
		String sql = "INSERT INTO " + tableName + "(" + name.toString() + ") values (" + parameter.toString() + ")";
		return sql;
	}

	@Override
	public T findById(String sql, long id, RowMapper<T> rowMapper) {
		T t = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = SingletonConnection.getInstance().getConnection();
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

	@Override
	public List<T> findAll() {
		ObjectMapper<T> objectMapper = new ObjectMapper<T>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = SingletonConnection.getInstance().getConnection();
			Class<T> zClass = getZClass();
			String tableName = "";
			if (zClass.isAnnotationPresent(Table.class)) {
				Table table = zClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			String sql = "select * from " + tableName;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			return objectMapper.maprow(resultSet, zClass);
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
