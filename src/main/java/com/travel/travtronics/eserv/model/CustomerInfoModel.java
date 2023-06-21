package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
@Entity
@Table(name = "customer_info")
public class CustomerInfoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUSTOMERID")
	private Long customerId;

	@Column(name = "BusinessName")
	private String businessName;

	@Column(name = "ShortName")
	private String shortName;

	@Column(name = "Type")
	private Integer type;
	
	@Column(name = "OrganizationId")
	private Long organizationId;
	
	@Column(name = "SrId")
	private Long srId;

	@Column(name = "Code")
	private String code;

	@Column(name = "Category")
	private Integer category;

	@Column(name = "Industry")
	private Integer industry;

	@Column(name = "SubIndustry")
	private Integer subIndustry;

	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "IsCustomer")
	private Boolean isCustomer;

	@Column(name = "Cust_RegistrationNumber")
	private Integer custRegistrationNumber;

	@Column(name = "IsSupplier")
	private Boolean isSupplier;

	@Column(name = "Suppl_RegistrationNumber")
	private Integer supplRegistrationNumber;

	@Column(name = "Rating")
	private Integer rating;

	@Column(name = "Legacy_ID")
	private String legacyID;

	@Column(name = "StatusWF")
	private Integer wfStatus;

	@Enumerated(EnumType.STRING)
	private Status status;

	@CreationTimestamp
	@Column(name = "CreatedDate",updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "CreatedBy",updatable = false)
	private Integer createdBy;

	@Column(name = "UpdatedBy")
	private Integer updatedBy;

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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	
	

}
