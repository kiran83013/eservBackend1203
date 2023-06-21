package com.travel.travtronics.request;

public class UnitSearchDto {
	
	private Long businessUnitId;
	
	private Long costCenter;
	
	private String locationId;
	
	private Long project;
	
	private Long category;
	
	private Long type;
	
	private Long buildingInformationId;

	private Long floorId;
	
	private Long unitId;
	
	private Long slotTemplateId;
	
	private Long shiftId;

	private String timeZone;
	
	private String shiftFrom;

	private String shiftTo;
	
	

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	public Long getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(Long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public Long getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(Long costCenter) {
		this.costCenter = costCenter;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Long getProject() {
		return project;
	}

	public void setProject(Long project) {
		this.project = project;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	

	public Long getFloorId() {
		return floorId;
	}

	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getShiftFrom() {
		return shiftFrom;
	}

	public void setShiftFrom(String shiftFrom) {
		this.shiftFrom = shiftFrom;
	}

	public String getShiftTo() {
		return shiftTo;
	}

	public void setShiftTo(String shiftTo) {
		this.shiftTo = shiftTo;
	}

	public Long getSlotTemplateId() {
		return slotTemplateId;
	}

	public void setSlotTemplateId(Long slotTemplateId) {
		this.slotTemplateId = slotTemplateId;
	}

	public Long getBuildingInformationId() {
		return buildingInformationId;
	}

	public void setBuildingInformationId(Long buildingInformationId) {
		this.buildingInformationId = buildingInformationId;
	}
	
	
}
