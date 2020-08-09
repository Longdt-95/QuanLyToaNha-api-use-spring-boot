package com.laptrinhjavaweb.repository.JDBC.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.Mapper.BuildingMapper;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.repository.JDBC.BuildingRepository;

public class BuildingRepositoryIMPL extends GenericRepoIMPL implements BuildingRepository {

	@Override
	public List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		List<BuildingDTO> results = new ArrayList<>();
		BuildingMapper buildingMapper = new BuildingMapper();
		String sql = "select * from building b join assignmentbuilding a on b.id = a.buildingid join user u on a.staffid = u.id where 1 = 1";
		sql = buildSQLBuildingSearch(buildingSearchBuilder, sql);
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

	private String buildSQLBuildingSearch(BuildingSearchBuilder buildingSearchBuilder, String sql) {
		StringBuilder stringBuilder = new StringBuilder(sql);
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (!field.getName().equals("chooseStaffNameAssimentBuilding")
						&& !field.getName().startsWith("rentPrice") && !field.getName().startsWith("rentArea")
						&& !field.getName().equals("types") && !field.getName().equals("staffNameAssimentBuilding")
						&& !field.getName().equals("staffPhoneAssimentBuilding")) {
					if (field.getType().getName().equals("java.lang.String")) {
						if (field.get(buildingSearchBuilder) != null) {
							stringBuilder.append(" and b." + field.getName().toLowerCase() + " like '%"
									+ field.get(buildingSearchBuilder) + "%'");
						}
					} else if (field.getType().getName().equals("java.lang.Integer")) {
						if (field.get(buildingSearchBuilder) != null) {
							stringBuilder.append(" and b." + field.getName().toLowerCase() + " = "
									+ field.get(buildingSearchBuilder));
						}
					}
				} else {
					if (field.getName().equals("rentPriceFrom") && field.get(buildingSearchBuilder) != null) {
						stringBuilder.append(" and " + field.get(buildingSearchBuilder) + " <= b.rentprice");
					} else if (field.getName().equals("rentPriceTo") && field.get(buildingSearchBuilder) != null) {
						stringBuilder.append(" and " + field.get(buildingSearchBuilder) + " >= b.rentprice");
					}
				}
			}
			String prefix = " and EXISTS (SELECT * FROM rentarea r WHERE r.buildingid = b.id AND (r.value";
			if (buildingSearchBuilder.getRentAreaFrom() != null & buildingSearchBuilder.getRentAreaTo() != null) {
				stringBuilder.append(prefix + "between " + buildingSearchBuilder.getRentAreaFrom() + " and "
								     										   + buildingSearchBuilder.getRentAreaTo() + "))");
			} else if (buildingSearchBuilder.getRentPriceFrom() != null) {
				stringBuilder.append(prefix + " >= " + buildingSearchBuilder.getRentPriceFrom() + "))");
			} else
				stringBuilder.append(prefix + " <= " + buildingSearchBuilder.getRentPriceTo() + "))");

			if (buildingSearchBuilder.getStaffNameAssimentBuilding() != null) {
				stringBuilder
						.append(" and u.fullname = '" + buildingSearchBuilder.getStaffNameAssimentBuilding() + "'");
			}
			if (buildingSearchBuilder.getStaffPhoneAssimentBuilding() != null) {
				stringBuilder.append(" and u.phone = '" + buildingSearchBuilder.getStaffPhoneAssimentBuilding() + "'");
			}
			if (buildingSearchBuilder.getTypes() != null) {
				int lengthType = buildingSearchBuilder.getTypes().length;
				stringBuilder.append(" and (b.type like '%" + buildingSearchBuilder.getTypes()[0] + "%'");
				for (int i =1; i < lengthType ; i++) {
					if ( i >= 1) {
						stringBuilder.append(" or b.type like '%" + buildingSearchBuilder.getTypes()[i] + "%'");
					}	
				}
				stringBuilder.append(")");
			}
			return stringBuilder.toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@SuppressWarnings("static-access")
	@Override
	public long saveBuilding(BuildingDTO buildingDTO) {
		String sql = "";
		sql = buildSQLInsertBuilding(buildingDTO);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		long id = -1;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
			setParameter(statement, buildingDTO);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			while (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
			return -1;
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

	private String buildSQLInsertBuilding(BuildingDTO buildingDTO) {
		Field[] fields = BuildingDTO.class.getDeclaredFields();
		StringBuilder name = new StringBuilder();
		StringBuilder parameter = new StringBuilder();
		String sql;
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				if (i >= 1 && !fields[i].getName().equals("rentArea")) {
					name.append(fields[i].getName().toLowerCase() + ",");
					parameter.append("?,");
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		name.deleteCharAt(name.toString().length() - 1);
		parameter.deleteCharAt(parameter.toString().length() - 1);
		sql = "INSERT INTO building (" + name.toString() + ") values (" + parameter.toString() + ")";
		return sql;
	}

	private String convertTypeToString(String[] type) {
		StringBuilder stringBuilder = new StringBuilder();
		String types;
		for (String string : type) {
			stringBuilder.append(string + ",");
		}
		types = stringBuilder.toString();
		return types.substring(0, types.length() - 1);
	}

	private void setParameter(PreparedStatement statement, BuildingDTO buildingDTO) {
		Field[] fields = BuildingDTO.class.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				if (i >= 1 && !fields[i].getName().equals("rentArea") && !fields[i].getName().equals("type")) {
					if (fields[i].get(buildingDTO) instanceof String) {
						statement.setString(i, (String) fields[i].get(buildingDTO));
					} else if (fields[i].get(buildingDTO) instanceof Integer) {
						statement.setInt(i, (Integer) fields[i].get(buildingDTO));
					} else if (fields[i].get(buildingDTO) instanceof Double) {
						statement.setDouble(i, (Double) fields[i].get(buildingDTO));
					} else if (fields[i].get(buildingDTO) == null) {
						statement.setNull(i, Types.NULL);
					}
				} else if (fields[i].getName().equals("type")) {
					statement.setString(i, convertTypeToString(buildingDTO.getType()));
				}
			} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
