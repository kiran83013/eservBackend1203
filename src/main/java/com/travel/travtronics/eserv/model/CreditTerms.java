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
import javax.validation.constraints.NotNull;

import com.travel.travtronics.enums.Status;


@Entity
@Table(name = "customer_creditterms")
public class CreditTerms extends WhoColumnsModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
//	@NotNull(message = "Company is Mandatory")
	@Column(name = "Company")
	private Long company;
	@Column(name = "Organization")
	private Long organization;
	@Column(name = "BusinessUnit")
	private Long businessUnit;
	@NotNull(message = "CostCenter is Mandatory")
	@Column(name = "CostCenter")
	private Long costCenter;
	@NotNull(message = "Location is Mandatory")
	@Column(name = "Location")
	private Long location;
	@NotNull(message = "CustomerId is Mandatory")
	@Column(name = "CustomerId")
	private Long customerId;
	@Column(name = "CreditLimit")
	private Long creditLimit;
	@Column(name = "ExtraCreditLimit")
	private Long extraCreditLimit;
	@Column(name = "AdRangeFrom")
	private Long adRangeFrom;
	@Column(name = "AsRangeTo")
	private Long adRangeTo;
	@Column(name = "TotalCreditLimit")
	private Long totalCreditLimit;
	@Column(name = "SecurityDeposite")
	private Long securityDeposite;
	@Column(name = "LegacyId")
	private String legacyId;
	@NotNull(message = "StartDate is Mandatory")
	@Column(name = "StartDate")
	private Date startDate;
	@Column(name = "EndDate")
	private Date endDate;
	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private Status status;
//	@Column(name = "CreatedBy")
//	private Long createdBy;
//	@Column(name = "UpdatedBy")
//	private Long updatedBy;
//	@Column(name = "CreatedDate")
//	private Date createdDate;
//	@Column(name = "UpdatedDate")
//	private Date updatedDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompany() {
		return company;
	}
	public void setCompany(Long company) {
		this.company = company;
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
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
	}
	public Long getExtraCreditLimit() {
		return extraCreditLimit;
	}
	public void setExtraCreditLimit(Long extraCreditLimit) {
		this.extraCreditLimit = extraCreditLimit;
	}
	public Long getAdRangeFrom() {
		return adRangeFrom;
	}
	public void setAdRangeFrom(Long adRangeFrom) {
		this.adRangeFrom = adRangeFrom;
	}
	public Long getAdRangeTo() {
		return adRangeTo;
	}
	public void setAdRangeTo(Long adRangeTo) {
		this.adRangeTo = adRangeTo;
	}
	public Long getTotalCreditLimit() {
		return totalCreditLimit;
	}
	public void setTotalCreditLimit(Long totalCreditLimit) {
		this.totalCreditLimit = totalCreditLimit;
	}
	public Long getSecurityDeposite() {
		return securityDeposite;
	}
	public void setSecurityDeposite(Long securityDeposite) {
		this.securityDeposite = securityDeposite;
	}
	public String getLegacyId() {
		return legacyId;
	}
	public void setLegacyId(String legacyId) {
		this.legacyId = legacyId;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
//	public Long getCreatedBy() {
//		return createdBy;
//	}
//	public void setCreatedBy(Long createdBy) {
//		this.createdBy = createdBy;
//	}
//	public Long getUpdatedBy() {
//		return updatedBy;
//	}
//	public void setUpdatedBy(Long updatedBy) {
//		this.updatedBy = updatedBy;
//	}
//	public Date getCreatedDate() {
//		return createdDate;
//	}
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//	public Date getUpdatedDate() {
//		return updatedDate;
//	}
//	public void setUpdatedDate(Date updatedDate) {
//		this.updatedDate = updatedDate;
//	}
}

