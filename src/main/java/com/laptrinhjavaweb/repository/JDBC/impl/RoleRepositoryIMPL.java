package com.laptrinhjavaweb.repository.JDBC.impl;

import com.laptrinhjavaweb.Mapper.RoleMapper;
import com.laptrinhjavaweb.Mapper.RowMapper;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.repository.JDBC.RoleRepository;

public class RoleRepositoryIMPL extends GenericRepoIMPL<RoleDTO> implements RoleRepository<RoleDTO> {

	@Override
	public RoleDTO findByIds(long id, RowMapper<RoleDTO> rowMapper) {
		String sql = "select * from role where id = ?";
		return findById(sql, id, new RoleMapper());
	}

	

}
