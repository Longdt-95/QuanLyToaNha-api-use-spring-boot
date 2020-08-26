package com.laptrinhjavaweb.constant;

import java.util.ArrayList;
import java.util.List;

public class District {
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private District(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static final District district = new District("QUAN_1", "Quận 1");
	public static final District district2 = new District("QUAN_2", "Quận 2");
	public static final District district3 = new District("QUAN_3", "Quận 3");
	public static final District district4 = new District("QUAN_4", "Quận 4");
			
	public static List<District> getLists() {
		List<District> listType = new ArrayList<>();
		listType.add(district);
		listType.add(district2);
		listType.add(district3);
		listType.add(district4);
		return listType;
	}
	
}
