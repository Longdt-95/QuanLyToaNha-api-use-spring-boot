package com.laptrinhjavaweb.api;

import java.util.EnumMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.Service.impl.BuildingServiceIMPL;
import com.laptrinhjavaweb.constant.District;
import com.laptrinhjavaweb.constant.TypeOfTransaction;

@RestController
public class TypeOfBuildingAPI {

	private BuildingServiceIMPL buildingService = new BuildingServiceIMPL();
	
	// USE CONSTANT
	@GetMapping ("/type-building")
	public List<com.laptrinhjavaweb.constant.TypeOfBuilding> getTypeOfBuilding() {
		return com.laptrinhjavaweb.constant.TypeOfBuilding.getLists();
	}
	
	@GetMapping("/district") 
	public List<District> getdistrict() {
		return District.getLists();
	}
	
	@GetMapping("/type-transaction")
	public List<TypeOfTransaction> getTypeTransaction() {
		return TypeOfTransaction.getLists();
	}
	
	//USE ENUM
	@GetMapping("districts")
	public EnumMap<com.laptrinhjavaweb.enums.District, String> getTypeOfBuilding1() {
		return buildingService.getdistricts();
	}
	
	
}
