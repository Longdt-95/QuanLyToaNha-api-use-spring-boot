package com.laptrinhjavaweb.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.dto.RoleDTO;

public class RoleMapper implements RowMapper<RoleDTO> {

	@Override
	public RoleDTO maprow(ResultSet resultSet) {
		RoleDTO roleDTO = new RoleDTO();
		try {
			roleDTO.setId(resultSet.getLong("id"));
			roleDTO.setName(resultSet.getString("name"));
			roleDTO.setCode(resultSet.getString("code"));
			return roleDTO;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
