package com.laptrinhjavaweb.enity;

import java.util.Date;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;

@Table (name = "building")
public class BuildingEntity {
	
	@Column (name = "id")
	private long id;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "street")
	private String street;
	
	@Column (name = "ward")
	private String ward;
	
	@Column (name = "district")
	private String district;
	
	@Column (name = "structure")
	private String structure;
	
	@Column (name = "numberofbasement")
	private Integer numberOfBasement;
	
	@Column (name = "floorarea")
	private Integer floorArea;
	
	@Column (name = "direction")
	private String direction;
	
	@Column (name = "level")
	private String level;
	
	@Column (name = "rentprice")
	private Integer rentPrice;
	
	@Column (name = "rentpricedescription")
	private String rentPriceDescription;
	
	@Column (name = "servicefee")
	private String serviceFee;
	
	@Column (name = "carfee")
	private String carFee;
	
	@Column (name = "motofee")
	private String motoFee;
	
	@Column (name = "overtimefee")
	private String overtimeFee;
	
	@Column (name = "waterfee")
	private String waterFee;
	
	@Column (name = "electricityfee")
	private String electricityFee;
	
	@Column (name = "deposit")
	private String deposit;
	
	@Column (name = "payment")
	private String payment;
	
	@Column (name = "renttime")
	private String rentTime;
	
	@Column (name = "decorationtime")
	private String decorationTime;
	
	@Column (name = "brokeragetee")
	private Double brokeragetee;
	
	@Column (name = "note")
	private String note;
	
	@Column (name = "linkofbuilding")
	private String linkOfBuilding;
	
	@Column (name = "map")
	private String map;
	
	@Column (name = "avatar")
	private String avatar;
	
	@Column (name = "type")
	private String type;
	
	@Column (name = "createdby")
	private String createdBy;
	
	@Column (name = "createddate")
	private Date createdDate;
	
	@Column (name = "modifiedby")
	private String modifiedBy;
	
	@Column (name = "modifieddate")
	private Date modifiedDate;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getRentPriceDescription() {
		return rentPriceDescription;
	}
	public void setRentPriceDescription(String rentPriceDescription) {
		this.rentPriceDescription = rentPriceDescription;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getCarFee() {
		return carFee;
	}
	public void setCarFee(String carFee) {
		this.carFee = carFee;
	}
	public String getMotoFee() {
		return motoFee;
	}
	public void setMotoFee(String motoFee) {
		this.motoFee = motoFee;
	}
	public String getOvertimeFee() {
		return overtimeFee;
	}
	public void setOvertimeFee(String overtimeFee) {
		this.overtimeFee = overtimeFee;
	}
	public String getWaterFee() {
		return waterFee;
	}
	public void setWaterFee(String waterFee) {
		this.waterFee = waterFee;
	}
	public String getElectricityFee() {
		return electricityFee;
	}
	public void setElectricityFee(String electricityFee) {
		this.electricityFee = electricityFee;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getRentTime() {
		return rentTime;
	}
	public void setRentTime(String rentTime) {
		this.rentTime = rentTime;
	}
	public String getDecorationTime() {
		return decorationTime;
	}
	public void setDecorationTime(String decorationTime) {
		this.decorationTime = decorationTime;
	}
	public Double getBrokeragetee() {
		return brokeragetee;
	}
	public void setBrokeragetee(Double brokeragetee) {
		this.brokeragetee = brokeragetee;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getLinkOfBuilding() {
		return linkOfBuilding;
	}
	public void setLinkOfBuilding(String linkOfBuilding) {
		this.linkOfBuilding = linkOfBuilding;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
