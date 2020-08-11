package com.laptrinhjavaweb.repository.JDBC;

import com.laptrinhjavaweb.Mapper.RowMapper;

public interface GenericRepo<T>{
	long save(String sql);
	T findById(String sql, long id,RowMapper<T> rowMapper);
}
