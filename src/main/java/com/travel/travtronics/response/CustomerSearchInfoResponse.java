package com.travel.travtronics.response;

import java.util.Date;

public class CustomerSearchInfoResponse {

	private Long customerId;

	private String businessName;

	private String shortName;

	private Integer type;

	private String typeName;

	private String code;

	private Integer category;

	private String categoryName;

	private Integer industry;

	private String industryName;

	private Integer subIndustry;

	private String subIndustryName;

	private Date startDate;

	private Date endDate;

	private Boolean isCustomer;

	private Integer custRegistrationNumber;

	private Boolean isSupplier;

	private Integer supplRegistrationNumber;

	private Integer rating;

	private String ratingName;

	private String legacyID;

	private Integer wfStatus;

	private String status;

	private Date createdDate;

	private Date updatedDate;

	private Integer createdBy;

	private Integer updatedBy;

	private Long businessUnitId;

	private Long organizationId;

	private Long locationId;

	private Long costCenterId;

	private String businessUnitName;
	private String organizationName;
	private String locationName;
	private String costCenterName;

	public CustomerSearchInfoResponse() {

	}

	public CustomerSearchInfoResponse(Long customerId, String businessName, String shortName, Integer type,
			String typeName, String code, Integer category, String categoryName, Integer industry, String industryName,
			Integer subIndustry, String subIndustryName, Date startDate, Date endDate, Boolean isCustomer,
			Integer custRegistrationNumber, Boolean isSupplier, Integer supplRegistrationNumber, Integer rating,
			String ratingName, String legacyID, Integer wfStatus, String status, Date createdDate, Date updatedDate,
			Integer createdBy, Integer updatedBy, Long businessUnitId, Long organizationId, Long locationId,
			Long costCenterId, String businessUnitName, String organizationName, String locationName,
			String costCenterName) {
		this.customerId = customerId;
		this.businessName = businessName;
		this.shortName = shortName;
		this.type = type;
		this.typeName = typeName;
		this.code = code;
		this.category = category;
		this.categoryName = categoryName;
		this.industry = industry;
		this.industryName = industryName;
		this.subIndustry = subIndustry;
		this.subIndustryName = subIndustryName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isCustomer = isCustomer;
		this.custRegistrationNumber = custRegistrationNumber;
		this.isSupplier = isSupplier;
		this.supplRegistrationNumber = supplRegistrationNumber;
		this.rating = rating;
		this.ratingName = ratingName;
		this.legacyID = legacyID;
		this.wfStatus = wfStatus;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.businessUnitId = businessUnitId;
		this.organizationId = organizationId;
		this.locationId = locationId;
		this.costCenterId = costCenterId;
		this.businessUnitName = businessUnitName;
		this.organizationName = organizationName;
		this.locationName = locationName;
		this.costCenterName = costCenterName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public Integer getSubIndustry() {
		return subIndustry;
	}

	public void setSubIndustry(Integer subIndustry) {
		this.subIndustry = subIndustry;
	}

	public String getSubIndustryName() {
		return subIndustryName;
	}

	public void setSubIndustryName(String subIndustryName) {
		this.subIndustryName = subIndustryName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(Boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public Integer getCustRegistrationNumber() {
		return custRegistrationNumber;
	}

	public void setCustRegistrationNumber(Integer custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}

	public Boolean getIsSupplier() {
		return isSupplier;
	}

	public void setIsSupplier(Boolean isSupplier) {
		this.isSupplier = isSupplier;
	}

	public Integer getSupplRegistrationNumber() {
		return supplRegistrationNumber;
	}

	public void setSupplRegistrationNumber(Integer supplRegistrationNumber) {
		this.supplRegistrationNumber = supplRegistrationNumber;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getRatingName() {
		return ratingName;
	}

	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}

	public String getLegacyID() {
		return legacyID;
	}

	public void setLegacyID(String legacyID) {
		this.legacyID = legacyID;
	}

	public Integer getWfStatus() {
		return wfStatus;
	}

	public void setWfStatus(Integer wfStatus) {
		this.wfStatus = wfStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(Long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getCostCenterId() {
		return costCenterId;
	}

	public void setCostCenterId(Long costCenterId) {
		this.costCenterId = costCenterId;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

}
