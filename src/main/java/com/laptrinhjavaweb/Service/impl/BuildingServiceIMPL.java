package com.laptrinhjavaweb.Service.impl;

import java.util.List;

import com.laptrinhjavaweb.Service.BuildingService;
import com.laptrinhjavaweb.Service.RentAreaBuildingService;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.repository.JDBC.BuildingRepository;
import com.laptrinhjavaweb.repository.JDBC.RentAreaBuildingRepo;
import com.laptrinhjavaweb.repository.JDBC.impl.BuildingRepositoryIMPL;
import com.laptrinhjavaweb.repository.JDBC.impl.RentAreaBuildingRepoIMPL;

public class BuildingServiceIMPL implements BuildingService {
	
	private BuildingRepository buildingRepository = new BuildingRepositoryIMPL();
	private RentAreaBuildingRepo rentAreaBuildingService = new RentAreaBuildingRepoIMPL();

	@Override
	public List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		return buildingRepository.getBuildings(buildingSearchBuilder);
	}

	@Override
	public BuildingDTO findById(BuildingDTO buildingDTO) {
		long id = -1;
		id = buildingRepository.saveBuilding(buildingDTO);
		if ( id != -1 ) {
			rentAreaBuildingService.saveRentAreaBuilding(buildingDTO, id);
		}
		return buildingRepository.findById(id);
	}

}
