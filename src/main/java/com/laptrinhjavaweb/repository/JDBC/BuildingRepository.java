package com.laptrinhjavaweb.repository.JDBC;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;

public interface BuildingRepository {
	List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder);
}
