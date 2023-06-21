package com.travel.travtronics.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
public class CustomerInfoResponse {

	private Long customerId;

	private String businessName;

	private String shortName;

	private Integer type;

	private Long organizationId;
	
	private Long srId;

	private String code;

	private Integer category;

	private Integer industry;

	private Integer subIndustry;

	private Date startDate;

	private Date endDate;

	private Boolean isCustomer;

	private Integer custRegistrationNumber;

	private Boolean isSupplier;

	private Integer supplRegistrationNumber;

	private Integer rating;

	private String legacyID;

	private Integer wfStatus;

	private Status status;

	private Date createdDate;

	private Date updatedDate;

	private Integer createdBy;

	private Integer updatedBy;

	private String typeName;

	private String categoryName;

	private String industryName;

	public String srName;
	
//	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String primaryConatct;

//	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String primaryEmail;

//	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String primaryPhoneNumber;

//	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long contactId;

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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
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

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	public Integer getSubIndustry() {
		return subIndustry;
	}

	public void setSubIndustry(Integer subIndustry) {
		this.subIndustry = subIndustry;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	
	
	
}
