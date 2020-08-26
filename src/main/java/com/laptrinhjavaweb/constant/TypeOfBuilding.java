package com.laptrinhjavaweb.constant;

import java.util.ArrayList;
import java.util.List;

public class TypeOfBuilding {
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private TypeOfBuilding(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static final TypeOfBuilding tang_tret = new TypeOfBuilding("TANG_TRET", "Tầng trệt");
	public static final TypeOfBuilding nguyen_can = new TypeOfBuilding("NGUYEN_CAN", "Nguyên căn");
	public static final TypeOfBuilding noi_that = new TypeOfBuilding("NOI_THAT", "Nội thất");
	
			
	public static List<TypeOfBuilding> getLists() {
		List<TypeOfBuilding> listType = new ArrayList<>();
		listType.add(tang_tret);
		listType.add(nguyen_can);
		listType.add(noi_that);
		return listType;
	}
}
