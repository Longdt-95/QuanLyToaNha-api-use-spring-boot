package com.laptrinhjavaweb.Mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {
	T maprow(ResultSet resultSet);
}
