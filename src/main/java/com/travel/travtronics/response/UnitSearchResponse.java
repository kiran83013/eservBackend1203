package com.travel.travtronics.response;

public class UnitSearchResponse {
	
	private Long costCenter;

	private String costCenterName;
	
	private Long businessUnitId;
	
	private String businessUnitName;
	
	private Long project;
	
	private String projectName;

	private Long category;
	
	private String categoryName;
	
	private Long type;
	
	private String typeName;
	
	private Long buildingInformationId;
	
	private String buildingName;
	
	private Long floorId;
	
	private String floorName;
	
	private String shiftFrom;

	private String shiftTo;

	private Long shiftId;

	private String shiftName;
	
	private Long unitId;
	
	private String unitName;
	
	private String location;

	private String locationName;
	
	private Long slotTemplateId;
	
	private String slotTemplateName;
	
	private String timeZone;
	
	private String timeZoneName;

	private Long unitShiftAssignmentId;
	
	private Long unitShiftSlotAssignmentId;
	
	private Long orgId;
	
	
	public UnitSearchResponse(Long costCenter, String costCenterName, Long businessUnitId, String businessUnitName,
			Long project, String projectName, Long category, String categoryName, Long type, String typeName,
			Long buildingInformationId, String buildingName, Long floorId, String floorName, String shiftFrom,
			String shiftTo, Long shiftId, String shiftName, Long unitId, String unitName, String location,
			String locationName, Long slotTemplateId, String slotTemplateName, String timeZone, String timeZoneName,
			Long unitShiftAssignmentId, Long unitShiftSlotAssignmentId, Long orgId) {
		super();
		this.costCenter = costCenter;
		this.costCenterName = costCenterName;
		this.businessUnitId = businessUnitId;
		this.businessUnitName = businessUnitName;
		this.project = project;
		this.projectName = projectName;
		this.category = category;
		this.categoryName = categoryName;
		this.type = type;
		this.typeName = typeName;
		this.buildingInformationId = buildingInformationId;
		this.buildingName = buildingName;
		this.floorId = floorId;
		this.floorName = floorName;
		this.shiftFrom = shiftFrom;
		this.shiftTo = shiftTo;
		this.shiftId = shiftId;
		this.shiftName = shiftName;
		this.unitId = unitId;
		this.unitName = unitName;
		this.location = location;
		this.locationName = locationName;
		this.slotTemplateId = slotTemplateId;
		this.slotTemplateName = slotTemplateName;
		this.timeZone = timeZone;
		this.timeZoneName = timeZoneName;
		this.unitShiftAssignmentId = unitShiftAssignmentId;
		this.unitShiftSlotAssignmentId = unitShiftSlotAssignmentId;
		this.orgId = orgId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(Long costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public Long getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(Long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public Long getProject() {
		return project;
	}

	public void setProject(Long project) {
		this.project = project;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getBuildingInformationId() {
		return buildingInformationId;
	}

	public void setBuildingInformationId(Long buildingInformationId) {
		this.buildingInformationId = buildingInformationId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Long getFloorId() {
		return floorId;
	}

	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
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

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Long getSlotTemplateId() {
		return slotTemplateId;
	}

	public void setSlotTemplateId(Long slotTemplateId) {
		this.slotTemplateId = slotTemplateId;
	}

	public String getSlotTemplateName() {
		return slotTemplateName;
	}

	public void setSlotTemplateName(String slotTemplateName) {
		this.slotTemplateName = slotTemplateName;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZoneName() {
		return timeZoneName;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	public Long getUnitShiftAssignmentId() {
		return unitShiftAssignmentId;
	}

	public void setUnitShiftAssignmentId(Long unitShiftAssignmentId) {
		this.unitShiftAssignmentId = unitShiftAssignmentId;
	}

	public Long getUnitShiftSlotAssignmentId() {
		return unitShiftSlotAssignmentId;
	}

	public void setUnitShiftSlotAssignmentId(Long unitShiftSlotAssignmentId) {
		this.unitShiftSlotAssignmentId = unitShiftSlotAssignmentId;
	}

	
	
}
