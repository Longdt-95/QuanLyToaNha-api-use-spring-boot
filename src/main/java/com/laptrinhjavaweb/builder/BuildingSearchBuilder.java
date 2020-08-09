package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {

	private String name;
	private String street;
	private String ward;
	private String district;
	private String level;
	private Integer numberOfBasement;
	private Integer floorArea;
	private String direction;
	private Integer rentAreaFrom;
	private Integer rentAreaTo;
	private Integer rentPriceFrom;
	private Integer rentPriceTo;
	private String staffNameAssimentBuilding;
	private String staffPhoneAssimentBuilding;
	private String chooseStaffNameAssimentBuilding;
	private String[] types = new String[] {};

	public Integer getRentAreaFrom() {
		return rentAreaFrom;
	}

	public Integer getRentAreaTo() {
		return rentAreaTo;
	}

	public Integer getRentPriceFrom() {
		return rentPriceFrom;
	}

	public Integer getRentPriceTo() {
		return rentPriceTo;
	}

	public String getStaffNameAssimentBuilding() {
		return staffNameAssimentBuilding;
	}

	public String getStaffPhoneAssimentBuilding() {
		return staffPhoneAssimentBuilding;
	}

	public String getChooseStaffNameAssimentBuilding() {
		return chooseStaffNameAssimentBuilding;
	}


	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getWard() {
		return ward;
	}

	public String getDistrict() {
		return district;
	}

	public String getLevel() {
		return level;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public String getDirection() {
		return direction;
	}

	private BuildingSearchBuilder(Builder builder) {
			this.name=builder.name;
			this.street=builder.street;
			this.direction=builder.direction;
			this.district=builder.district;
			this.numberOfBasement=builder.numberOfBasement;
			this.ward=builder.ward;
			this.floorArea=builder.floorArea;
			this.level=builder.level;
			this.chooseStaffNameAssimentBuilding=builder.chooseStaffNameAssimentBuilding;
			this.rentAreaFrom=builder.rentAreaFrom;
			this.rentAreaTo=builder.rentAreaTo;
			this.rentPriceFrom=builder.rentPriceFrom;
			this.rentPriceTo=builder.rentPriceTo;
			this.staffNameAssimentBuilding=builder.staffNameAssimentBuilding;
			this.staffPhoneAssimentBuilding=builder.staffPhoneAssimentBuilding;
			this.types=builder.types;
		}

	public String[] getTypes() {
		return types;
	}

	// Builder Class
	public static class Builder {

		private String name;
		private String street;
		private String ward;
		private String district;
		private String level;
		private Integer numberOfBasement;
		private Integer floorArea;
		private String direction;
		private Integer rentAreaFrom;
		private Integer rentAreaTo;
		private Integer rentPriceFrom;
		private Integer rentPriceTo;
		private String staffNameAssimentBuilding;
		private String staffPhoneAssimentBuilding;
		private String chooseStaffNameAssimentBuilding;
		private String[] types = new String[] {};


		public Builder setRentAreaFrom(Integer rentAreaFrom) {
			this.rentAreaFrom = rentAreaFrom;
			return this;
		}

		public Builder setRentAreaTo(Integer rentAreaTo) {
			this.rentAreaTo = rentAreaTo;
			return this;
		}

		public Builder setRentPriceFrom(Integer rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}

		public Builder setRentPriceTo(Integer rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}

		public Builder setStaffNameAssimentBuilding(String staffNameAssimentBuilding) {
			this.staffNameAssimentBuilding = staffNameAssimentBuilding;
			return this;
		}

		public Builder setStaffPhoneAssimentBuilding(String staffPhoneAssimentBuilding) {
			this.staffPhoneAssimentBuilding = staffPhoneAssimentBuilding;
			return this;
		}

		public Builder setChooseStaffNameAssimentBuilding(String chooseStaffNameAssimentBuilding) {
			this.chooseStaffNameAssimentBuilding = chooseStaffNameAssimentBuilding;
			return this;
		}


		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}

		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}

		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}

		public Builder setFloorArea(Integer floorarea) {
			this.floorArea = floorarea;
			return this;
		}

		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}
		
		public Builder setTypes(String[] types) {
			this.types = types;
			return this;
		}
		
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}


	}
}
