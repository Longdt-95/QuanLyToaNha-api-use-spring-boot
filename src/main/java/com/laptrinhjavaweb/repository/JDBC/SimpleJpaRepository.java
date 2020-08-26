package com.laptrinhjavaweb.repository.JDBC;

import java.util.List;

import com.laptrinhjavaweb.Mapper.RowMapper;

public interface SimpleJpaRepository<T> {
	
	long save(Object object);
	T findById(String sql, long id,RowMapper<T> rowMapper);
	List<T> findAll();
}
