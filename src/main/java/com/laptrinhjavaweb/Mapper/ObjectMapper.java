package com.laptrinhjavaweb.Mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;



import com.laptrinhjavaweb.annotation.Column;

public class ObjectMapper<T> {
	public List<T> maprow(ResultSet resultSet, Class<T> zClass) {
		List<T> listResult = new ArrayList<T>();
		try {
			Field[] fields = zClass.getDeclaredFields();
			T object = zClass.newInstance();
			while (resultSet.next()) {
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					String columnName = resultSetMetaData.getColumnName(i + 1);
					Object columnValue = resultSet.getObject(columnName);
					for (Field field: fields) {
						field.setAccessible(true);
						Column column = field.getAnnotation(Column.class);
						if (column.name().equals(columnName) && columnValue != null) {
							field.set(object, columnValue);
							break;
						}
					}
				}
				listResult.add(object);
			}
			return listResult;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
