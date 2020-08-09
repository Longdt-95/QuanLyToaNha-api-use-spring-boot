package com.laptrinhjavaweb.Service;

import com.laptrinhjavaweb.dto.BuildingDTO;

public interface RentAreaBuildingService {
	long[] saveRentAreaBuilding(BuildingDTO buildingDTO, long id);
}
