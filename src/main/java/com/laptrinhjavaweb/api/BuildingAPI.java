package com.laptrinhjavaweb.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.Service.BuildingService;
import com.laptrinhjavaweb.Service.RentAreaBuildingService;
import com.laptrinhjavaweb.Service.impl.BuildingServiceIMPL;
import com.laptrinhjavaweb.Service.impl.RentAreaBuildingServiceIMPL;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;

@RestController
public class BuildingAPI {

	private BuildingService buildingService = new BuildingServiceIMPL();
	private RentAreaBuildingService rentAreaService = new RentAreaBuildingServiceIMPL();
	

	@GetMapping("/buildings")
	public List<BuildingDTO> getBuildings(@RequestParam Map<String, String> requestParams, @RequestParam String[] type) {
		BuildingSearchBuilder buildingSearchBuilder = convertMapToBuilder(requestParams, type);
		return buildingService.getBuildings(buildingSearchBuilder);
	}

	@PostMapping ("/buildings")
	public long saveBuilding(@RequestBody BuildingDTO buildingDTO) {
		long id;
		id = buildingService.saveBuilding(buildingDTO);
		rentAreaService.saveRentAreaBuilding(buildingDTO, id);
		return id;
	}
	
	
	private BuildingSearchBuilder convertMapToBuilder(Map<String, String> requestParams, String[] type) {

		Integer numberOfBasement = requestParams.containsKey("numberOfBasement")
				? (StringUtils.isNoneBlank(requestParams.get("numberOfBasement"))
						? Integer.parseInt(requestParams.get("numberOfBasement"))
						: null)
				: null;
		Integer floorarea = requestParams.containsKey("floorarea")
				? (StringUtils.isNoneBlank(requestParams.get("floorarea"))
						? Integer.parseInt(requestParams.get("floorarea"))
						: null)
				: null;
		Integer rentAreaFrom = requestParams.containsKey("rentAreaFrom")
				? (StringUtils.isNoneBlank(requestParams.get("rentAreaFrom"))
						? Integer.parseInt(requestParams.get("rentAreaFrom"))
						: null)
				: null;
		Integer rentAreaTo = requestParams.containsKey("rentAreaTo")
				? (StringUtils.isNoneBlank(requestParams.get("rentAreaTo"))
						? Integer.parseInt(requestParams.get("rentAreaTo"))
						: null)
				: null;
		Integer rentPriceFrom = requestParams.containsKey("rentPriceFrom")
				? (StringUtils.isNoneBlank(requestParams.get("rentPriceFrom"))
						? Integer.parseInt(requestParams.get("rentPriceFrom"))
						: null)
				: null;
		Integer rentPriceTo = requestParams.containsKey("rentPriceTo")
				? (StringUtils.isNoneBlank(requestParams.get("rentPriceTo"))
						? Integer.parseInt(requestParams.get("rentPriceTo"))
						: null)
				: null;
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(requestParams.containsKey("name") ? requestParams.get("name") : null)
				.setNumberOfBasement(numberOfBasement)
				.setDirection(requestParams.containsKey("direction") ? requestParams.get("direction") : null)
				.setDistrict(requestParams.containsKey("district") ? requestParams.get("district") : null)
				.setFloorArea(floorarea)
				.setStreet(requestParams.containsKey("street") ? requestParams.get("street") : null)
				.setLevel(requestParams.containsKey("level") ? requestParams.get("level") : null)
				.setWard(requestParams.containsKey("ward") ? requestParams.get("ward") : null)
				.setRentAreaFrom(rentAreaFrom).setRentAreaTo(rentAreaTo).setRentPriceFrom(rentPriceFrom)
				.setRentPriceTo(rentPriceTo)
				.setStaffNameAssimentBuilding(requestParams.containsKey("staffNameAssimentBuilding")
						? requestParams.get("staffNameAssimentBuilding")
						: null)
				.setChooseStaffNameAssimentBuilding(requestParams.containsKey("chooseStaffNameAssimentBuilding")
						? requestParams.get("chooseStaffNameAssimentBuilding")
						: null)
				.setStaffPhoneAssimentBuilding(requestParams.containsKey("staffPhoneAssimentBuilding")
						? requestParams.get("staffPhoneAssimentBuilding")
						: null)
				.setTypes(type)
				.build();
		return builder;
	}
}
