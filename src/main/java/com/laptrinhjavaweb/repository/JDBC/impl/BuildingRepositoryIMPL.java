package com.laptrinhjavaweb.repository.JDBC.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.Mapper.BuildingMapper;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.enity.BuildingEntity;
import com.laptrinhjavaweb.repository.JDBC.BuildingRepository;

public class BuildingRepositoryIMPL extends SimpleJpaRepositoryIMPL<BuildingEntity> implements BuildingRepository {

	@Override
	public List<BuildingEntity> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		List<BuildingEntity> results = new ArrayList<>();
		BuildingMapper buildingMapper = new BuildingMapper();
		String sql = "select * from building b join assignmentbuilding a on b.id = a.buildingid join user u on a.staffid = u.id where 1 = 1";
		sql = buildSQLBuildingSearch(buildingSearchBuilder, sql);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = SingletonConnection.getInstance().getConnection();
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
				for (int i = 1; i < lengthType; i++) {
					if (i >= 1) {
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

	@SuppressWarnings({ "unused", "static-access" })
	public long saveWithTransaction(BuildingDTO buildingDTO) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		long buildingId = -1;
		try {
			connection = SingletonConnection.getInstance().getConnection();
			connection.setAutoCommit(false);
			String sqlInsertBuilding = buildSQLInsertBuilding(buildingDTO);
			statement = connection.prepareStatement(sqlInsertBuilding, statement.RETURN_GENERATED_KEYS);
			setParameter(statement, buildingDTO);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			while (resultSet.next()) {
				buildingId = resultSet.getLong(1);
			}
			String sqlInsertBuildingRentArea = "INSERT INTO rentarea(value, buildingid) values (?,?)";
			statement = connection.prepareStatement(sqlInsertBuildingRentArea, statement.RETURN_GENERATED_KEYS);
			for (String string : buildingDTO.getRentAreas()) {
				statement.setInt(1, Integer.parseInt(string));
				statement.setLong(2, buildingId);
				statement.executeUpdate();
			}
			connection.commit();
			return buildingId;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
			return buildingId;
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
				return buildingId;
			}
		}

	}

	// buildSQLQuery insertBuilding
	private String buildSQLInsertBuilding(BuildingDTO buildingDTO) {
		Field[] fields = BuildingDTO.class.getDeclaredFields();
		StringBuilder name = new StringBuilder();
		StringBuilder parameter = new StringBuilder();
		String sql;
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				if (i >= 1 && !fields[i].getName().equals("rentArea") && !fields[i].getName().equals("rentAreas")) {
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

	// convert Type from array to String
	private String convertTypeToString(String[] type) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String string : type) {
			stringBuilder.append(string + ",");
		}
		String types = stringBuilder.toString();
		return types.substring(0, types.length() - 1);
	}

	// set param for sql insertBuilding
	private void setParameter(PreparedStatement statement, BuildingDTO buildingDTO) {
		Field[] fields = BuildingDTO.class.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				int index = 1;
				for (Field field : buildingDTO.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if (!field.getName().equals("id") && !field.getName().equals("rentArea") 
													&& !field.getName().equals("rentAreas") && !field.getName().equals("type")) {
						statement.setObject(index, field.get(buildingDTO));
						index++;
					}else if(field.getName().equals("type")) {
						String type = convertTypeToString(buildingDTO.getType());
						statement.setObject(index, type);
						index++;
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@SuppressWarnings("unused")
	@Override
	public BuildingEntity findById(long id) {
		BuildingEntity buildingEntity = new BuildingEntity();
		String sql = "select * from building where id = ?";
		return buildingEntity = findById(sql, id, new BuildingMapper());
	}

}
