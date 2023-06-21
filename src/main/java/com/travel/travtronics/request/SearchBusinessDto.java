package com.travel.travtronics.request;

import java.util.Date;

public class SearchBusinessDto {
	private String businessName;
	private Long customerId;
	private Integer type;
	private Integer category;
	private Integer vertical;
	private Integer industry;
	private Date startDate;
	private Date endDate;
	private Integer custRegistrationNumber;
	private Long businessUnit;
	private Long organization;
	private Long location;
	private Long costCenter;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getVertical() {
		return vertical;
	}

	public void setVertical(Integer vertical) {
		this.vertical = vertical;
	}

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	public Integer getCustRegistrationNumber() {
		return custRegistrationNumber;
	}

	public void setCustRegistrationNumber(Integer custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
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

	public Long getOrganization() {
		return organization;
	}

	public void setOrganization(Long organization) {
		this.organization = organization;
	}

	public Long getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(Long businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Long getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(Long costCenter) {
		this.costCenter = costCenter;
	}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

}
