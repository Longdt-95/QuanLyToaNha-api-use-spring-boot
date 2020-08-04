package com.laptrinhjavaweb.Service.impl;

import java.util.List;

import com.laptrinhjavaweb.Service.BuildingService;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.repository.JDBC.BuildingRepository;
import com.laptrinhjavaweb.repository.JDBC.impl.BuildingRepositoryIMPL;

public class BuildingServiceIMPL implements BuildingService {
	
	private BuildingRepository buildingRepository = new BuildingRepositoryIMPL();

	@Override
	public List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		return buildingRepository.getBuildings(buildingSearchBuilder);
	}
	
}
