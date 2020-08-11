package com.laptrinhjavaweb.repository.JDBC;

import com.laptrinhjavaweb.Mapper.RowMapper;

public interface RoleRepository<T> extends GenericRepo<T>{
	T findByIds( long id, RowMapper<T> rowMapper);
}
