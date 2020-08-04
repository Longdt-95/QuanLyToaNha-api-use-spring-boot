package com.laptrinhjavaweb.repository.JDBC.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.Mapper.BuildingMapper;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.repository.JDBC.BuildingRepository;

public class BuildingRepositoryIMPL implements BuildingRepository {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = ("jdbc:mysql://localhost:3306/javaweb62020module1");
			String user = "root";
			String password = "saker0905971230";
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		List<BuildingDTO> results = new ArrayList<>();
		BuildingMapper buildingMapper = new BuildingMapper();
		String sql = "select * from building b where 1 = 1";
		sql = buiSQLBuildingSearch(buildingSearchBuilder, sql);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				results.add(buildingMapper.maprow(resultSet));
			}
		} catch (SQLException e) {
			return null;
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
				return null;
			}
		}
		return results;
	}

	private String buiSQLBuildingSearch(BuildingSearchBuilder buildingSearchBuilder, String sql) {
		StringBuilder stringBuilder = new StringBuilder(sql);
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (!field.getName().equals("chooseStaffNameAssimentBuilding") && !field.getName().startsWith("rent")) {
					if (field.getType().getName().equals("java.lang.String")) {
						if ( field.get(buildingSearchBuilder) != null) {
							stringBuilder.append(" and b." + field.getName().toLowerCase() + " like '%" + field.get(buildingSearchBuilder) + "%'");
						}
					} else if (field.getType().getName().equals("java.lang.Integer")) {
						if (field.get(buildingSearchBuilder) != null) {
						stringBuilder.append(" and b." + field.getName().toLowerCase() + " = " + field.get(buildingSearchBuilder));
						}
					}
				}
			}
			return stringBuilder.toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}
