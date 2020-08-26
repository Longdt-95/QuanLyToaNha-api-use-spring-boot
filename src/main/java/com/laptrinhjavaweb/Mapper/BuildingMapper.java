package com.laptrinhjavaweb.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.enity.BuildingEntity;


public class BuildingMapper implements RowMapper<BuildingEntity>{

	@Override
	public BuildingEntity maprow(ResultSet resultSet) {
		BuildingEntity buildingEntity = new BuildingEntity();
		try {
			buildingEntity.setName(resultSet.getString("name"));
			buildingEntity.setId(resultSet.getLong("id"));
			buildingEntity.setStreet(resultSet.getString("street"));
			buildingEntity.setWard(resultSet.getString("ward"));
			buildingEntity.setDistrict(resultSet.getString("district"));
			buildingEntity.setStructure(resultSet.getString("structure"));
			buildingEntity.setNumberOfBasement(resultSet.getInt("numberofbasement"));
			buildingEntity.setFloorArea(resultSet.getInt("floorarea"));
			buildingEntity.setDirection(resultSet.getString("direction"));
			buildingEntity.setLevel(resultSet.getString("level"));
			buildingEntity.setRentPrice(resultSet.getInt("rentprice"));
			buildingEntity.setRentPriceDescription(resultSet.getString("rentpricedescription"));
			buildingEntity.setServiceFee(resultSet.getString("servicefee"));
			buildingEntity.setCarFee(resultSet.getString("carfee"));
			buildingEntity.setMotoFee(resultSet.getString("motofee"));
			buildingEntity.setOvertimeFee(resultSet.getString("overtimefee"));
			buildingEntity.setCarFee(resultSet.getString("waterfee"));
			buildingEntity.setDeposit(resultSet.getString("deposit"));
			buildingEntity.setPayment(resultSet.getString("payment"));
			buildingEntity.setRentTime(resultSet.getString("renttime"));
			buildingEntity.setDecorationTime(resultSet.getString("decorationtime"));
			buildingEntity.setNote(resultSet.getString("note"));
			buildingEntity.setLinkOfBuilding(resultSet.getString("linkofbuilding"));
			buildingEntity.setCreatedDate(resultSet.getDate("createddate"));
			buildingEntity.setModifiedDate(resultSet.getDate("modifieddate"));
			buildingEntity.setCreatedBy(resultSet.getString("createdby"));
			buildingEntity.setModifiedBy(resultSet.getString("modifiedby"));
			return buildingEntity;
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
		return null;
	}
}
