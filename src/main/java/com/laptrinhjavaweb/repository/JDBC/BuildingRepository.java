package com.laptrinhjavaweb.repository.JDBC;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.enity.BuildingEntity;

public interface BuildingRepository extends SimpleJpaRepository<BuildingEntity>{
	List<BuildingEntity> getBuildings(BuildingSearchBuilder buildingSearchBuilder);
	long saveWithTransaction(BuildingDTO buildingDTO);
	BuildingEntity findById(long id);
	
	
}
