package com.laptrinhjavaweb.enums;

public enum TypeOfBuilding {
	TANG_TRET ("Tầng trệt"),
	NGUYEN_CAN ("Nguyên Căn"),
	NOI_THAT ("Nội thất");
	
	private String typeValue;
	TypeOfBuilding(String typeValue) {
		this.typeValue = typeValue;
	}
	public String getValue() {
		return this.typeValue;
	}

}
