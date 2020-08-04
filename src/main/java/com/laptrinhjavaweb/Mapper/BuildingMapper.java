package com.laptrinhjavaweb.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.laptrinhjavaweb.dto.BuildingDTO;


public class BuildingMapper implements RowMapper<BuildingDTO>{

	@Override
	public BuildingDTO maprow(ResultSet resultSet) {
		BuildingDTO buildingDTO = new BuildingDTO();
		try {
			buildingDTO.setName(resultSet.getString("name"));
			buildingDTO.setId(resultSet.getLong("id"));
			buildingDTO.setStreet(resultSet.getString("street"));
			buildingDTO.setWard(resultSet.getString("ward"));
			buildingDTO.setDistrict(resultSet.getString("district"));
			buildingDTO.setStructure(resultSet.getString("structure"));
			buildingDTO.setNumberOfBasement(resultSet.getInt("numberofbasement"));
			buildingDTO.setFloorarea(resultSet.getInt("floorarea"));
			buildingDTO.setDirection(resultSet.getString("direction"));
			buildingDTO.setLevel(resultSet.getString("level"));
			buildingDTO.setRentprice(resultSet.getInt("rentprice"));
			buildingDTO.setRentpricedescription(resultSet.getString("rentpricedescription"));
			buildingDTO.setServicefee(resultSet.getString("servicefee"));
			buildingDTO.setCarfee(resultSet.getString("carfee"));
			buildingDTO.setMotofee(resultSet.getString("motofee"));
			buildingDTO.setOvertimefee(resultSet.getString("overtimefee"));
			buildingDTO.setWaterfee(resultSet.getString("waterfee"));
			buildingDTO.setDeposit(resultSet.getString("deposit"));
			buildingDTO.setPayment(resultSet.getString("payment"));
			buildingDTO.setRenttime(resultSet.getString("renttime"));
			buildingDTO.setDecorationtime(resultSet.getString("decorationtime"));
			buildingDTO.setNote(resultSet.getString("note"));
			buildingDTO.setLinkofbuilding(resultSet.getString("linkofbuilding"));
			buildingDTO.setCreateddate(resultSet.getDate("createddate"));
			buildingDTO.setModifieddate(resultSet.getDate("modifieddate"));
			buildingDTO.setCreatedby(resultSet.getString("createdby"));
			buildingDTO.setModifiedby(resultSet.getString("modifiedby"));
			return buildingDTO;
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
		return null;
	}
}
