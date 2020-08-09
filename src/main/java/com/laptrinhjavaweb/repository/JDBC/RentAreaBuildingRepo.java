package com.laptrinhjavaweb.repository.JDBC;

import com.laptrinhjavaweb.dto.BuildingDTO;

public interface RentAreaBuildingRepo {
	long[] saveRentAreaBuilding(BuildingDTO buildingDTO, long id);
}
