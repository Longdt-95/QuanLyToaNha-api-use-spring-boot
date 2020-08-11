package com.laptrinhjavaweb.repository.JDBC.impl;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.repository.JDBC.RentAreaBuildingRepo;

public class RentAreaBuildingRepoIMPL extends GenericRepoIMPL<Object> implements RentAreaBuildingRepo {

	@Override
	public long[] saveRentAreaBuilding(BuildingDTO buildingDTO, long id) {

		long[] idRentArea = new long[] {};
		String[] rentAreaValues = buildingDTO.getRentArea().split(",");
		for (int i = 0; i < rentAreaValues.length; i++) {
			String sql = "INSERT INTO rentarea (value, buildingid) values (" + rentAreaValues[i] + "," + id + ")";
			save(sql);
		}
		return idRentArea;
	}
	
	


}
