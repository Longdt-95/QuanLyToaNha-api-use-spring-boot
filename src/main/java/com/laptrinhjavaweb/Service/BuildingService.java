package com.laptrinhjavaweb.Service;

import java.util.EnumMap;
import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.enums.District;
import com.laptrinhjavaweb.enums.TypeOfBuilding;
import com.laptrinhjavaweb.enums.TypeOfTransaction;

public interface BuildingService {
	List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder);
	BuildingDTO saveBuilding(BuildingDTO buildingDTO);
	
	
	/*// get type of building use constant
		List<String> getTypeOfBuilding();
		
	// get list district use constant
		List<String> getDistricts();*/
		
	// get type of building user enum
	EnumMap<TypeOfBuilding, String> getTypeOfBuildings();
		
	EnumMap<District, String> getdistricts();
	EnumMap<TypeOfTransaction, String> getTypeOfTransaction();
}
