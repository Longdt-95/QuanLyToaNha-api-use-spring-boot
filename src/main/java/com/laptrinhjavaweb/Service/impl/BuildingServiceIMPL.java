package com.laptrinhjavaweb.Service.impl;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import com.laptrinhjavaweb.Convertor.BuildingConvertor;
import com.laptrinhjavaweb.Service.BuildingService;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.enity.BuildingEntity;
import com.laptrinhjavaweb.enums.District;
import com.laptrinhjavaweb.enums.TypeOfBuilding;
import com.laptrinhjavaweb.enums.TypeOfTransaction;
import com.laptrinhjavaweb.repository.JDBC.BuildingRepository;
import com.laptrinhjavaweb.repository.JDBC.impl.BuildingRepositoryIMPL;

public class BuildingServiceIMPL implements BuildingService {
	
	private BuildingRepository buildingRepository = new BuildingRepositoryIMPL();
	private BuildingConvertor buildingConvetor = new BuildingConvertor();
	

	@Override
	public List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		List<BuildingEntity> listBuildingEntitie = buildingRepository.getBuildings(buildingSearchBuilder);
		List<BuildingDTO> listBuildingDTO = new ArrayList<BuildingDTO>();
		BuildingDTO buildingDTO = new BuildingDTO();
		for (BuildingEntity buildingEntity : listBuildingEntitie) {
			buildingDTO = buildingConvetor.convertToBuildingDTO(buildingEntity);
			listBuildingDTO.add(buildingDTO);
		}
		return listBuildingDTO;
	}

	@Override
	public BuildingDTO saveBuilding(BuildingDTO buildingDTO) {
		buildingDTO.setRentAreas(buildingDTO.getRentArea().split(","));
		BuildingDTO buildingDTOResult = new BuildingDTO();
		long id = buildingRepository.saveWithTransaction(buildingDTO);
		buildingDTOResult = buildingConvetor.convertToBuildingDTO(buildingRepository.findById(id));
		return buildingDTOResult;
	}

	
	

	@Override
	public EnumMap<com.laptrinhjavaweb.enums.TypeOfBuilding, String> getTypeOfBuildings() {
		EnumMap<com.laptrinhjavaweb.enums.TypeOfBuilding, String> map = new EnumMap<com.laptrinhjavaweb.enums.TypeOfBuilding, String>(com.laptrinhjavaweb.enums.TypeOfBuilding.class);
		for (TypeOfBuilding typeOfBuilding : TypeOfBuilding.values()) {
			map.put(typeOfBuilding, typeOfBuilding.getValue());
		}
		return map;
	}

	@Override
	public EnumMap<District, String> getdistricts() {
		EnumMap<com.laptrinhjavaweb.enums.District, String> map = new EnumMap<com.laptrinhjavaweb.enums.District, String>(com.laptrinhjavaweb.enums.District.class);
		for (District district : District.values()) {
			map.put(district, district.getValue());
		}
		return map;
	}

	@Override
	public EnumMap<TypeOfTransaction, String> getTypeOfTransaction() {
		EnumMap<com.laptrinhjavaweb.enums.TypeOfTransaction, String> map = new EnumMap<com.laptrinhjavaweb.enums.TypeOfTransaction, String>(com.laptrinhjavaweb.enums.TypeOfTransaction.class);
		for (TypeOfTransaction typeOfTransaction : TypeOfTransaction.values()) {
			map.put(typeOfTransaction, typeOfTransaction.getValue());
		}
		return map;
	}

	
	
	

}
