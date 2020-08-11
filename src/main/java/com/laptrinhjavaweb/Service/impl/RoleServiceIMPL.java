package com.laptrinhjavaweb.Service.impl;

import com.laptrinhjavaweb.Mapper.RoleMapper;
import com.laptrinhjavaweb.Service.RoleService;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.repository.JDBC.impl.RoleRepositoryIMPL;

public class RoleServiceIMPL implements RoleService {
	
	private RoleRepositoryIMPL roleRepo = new RoleRepositoryIMPL();

	@Override
	public RoleDTO findById(long id) {
		return roleRepo.findByIds(id, new RoleMapper());
	}

}
