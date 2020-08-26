package com.laptrinhjavaweb.constant;

import java.util.ArrayList;
import java.util.List;

public class TypeOfTransaction {
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private TypeOfTransaction(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static final TypeOfTransaction type_1 = new TypeOfTransaction("TYPE_1", "Quá trình chăm sóc khách hàng");
	public static final TypeOfTransaction type_2 = new TypeOfTransaction("TYPE_2", "dẫn đi xem");
			
	public static List<TypeOfTransaction> getLists() {
		List<TypeOfTransaction> listType = new ArrayList<>();
		listType.add(type_1);
		listType.add(type_2);
		return listType;
	}
}
