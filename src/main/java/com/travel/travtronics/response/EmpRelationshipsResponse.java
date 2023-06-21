package com.travel.travtronics.response;

import java.util.Date;

import com.travel.travtronics.enums.Status;

public class EmpRelationshipsResponse {
	private Long relationShipId;

	private Long firstPartyId;

	private Long organizationId;

	private Long secondPartyId;

	private String secondPartyTypeId;

	private Long fromRelationId;

	private Long toRelationId;

	private Date fromDate;

	private Date toDate;

	private Status status;

	private Long createdBy;

	private Long updatedBy;

	private Date createdDate;

	private Date updatedDate;
	
	private String firstPartyIdName;

	private String secondPartyIdName;

	private String fromRelationIdName;
	
	private String toRelationIdName;

	public Long getRelationShipId() {
		return relationShipId;
	}

	public void setRelationShipId(Long relationShipId) {
		this.relationShipId = relationShipId;
	}

	public Long getFirstPartyId() {
		return firstPartyId;
	}

	public void setFirstPartyId(Long firstPartyId) {
		this.firstPartyId = firstPartyId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getSecondPartyId() {
		return secondPartyId;
	}

	public void setSecondPartyId(Long secondPartyId) {
		this.secondPartyId = secondPartyId;
	}

	public String getSecondPartyTypeId() {
		return secondPartyTypeId;
	}

	public void setSecondPartyTypeId(String secondPartyTypeId) {
		this.secondPartyTypeId = secondPartyTypeId;
	}

	public Long getFromRelationId() {
		return fromRelationId;
	}

	public void setFromRelationId(Long fromRelationId) {
		this.fromRelationId = fromRelationId;
	}

	public Long getToRelationId() {
		return toRelationId;
	}

	public void setToRelationId(Long toRelationId) {
		this.toRelationId = toRelationId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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

	public String getFirstPartyIdName() {
		return firstPartyIdName;
	}

	public void setFirstPartyIdName(String firstPartyIdName) {
		this.firstPartyIdName = firstPartyIdName;
	}

	public String getSecondPartyIdName() {
		return secondPartyIdName;
	}

	public void setSecondPartyIdName(String secondPartyIdName) {
		this.secondPartyIdName = secondPartyIdName;
	}

	public String getFromRelationIdName() {
		return fromRelationIdName;
	}

	public void setFromRelationIdName(String fromRelationIdName) {
		this.fromRelationIdName = fromRelationIdName;
	}

	public String getToRelationIdName() {
		return toRelationIdName;
	}

	public void setToRelationIdName(String toRelationIdName) {
		this.toRelationIdName = toRelationIdName;
	}
	
	
}
