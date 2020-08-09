package com.laptrinhjavaweb.Service.impl;

import com.laptrinhjavaweb.Service.RentAreaBuildingService;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.repository.JDBC.RentAreaBuildingRepo;
import com.laptrinhjavaweb.repository.JDBC.impl.RentAreaBuildingRepoIMPL;

public class RentAreaBuildingServiceIMPL implements RentAreaBuildingService{

	private RentAreaBuildingRepo rentAreaRepo = new RentAreaBuildingRepoIMPL();
	@Override
	public long[] saveRentAreaBuilding(BuildingDTO buildingDTO, long id) {
		return rentAreaRepo.saveRentAreaBuilding(buildingDTO, id);
	}

}
