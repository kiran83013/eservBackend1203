package com.travel.travtronics.response;

import java.time.LocalDate;
import java.util.Date;

import com.travel.travtronics.enums.Status;

public class PersonRelationsResponse {
	private Long relationShipId;

	private Long firstPartyId;

	private Long secondPartyId;

	private String secondPartyTypeId;

	private Long fromRelationId;

	private Long toRelationId;

	private LocalDate fromDate;

	private LocalDate toDate;

	private Status status;

	private Long createdBy;

	private Long updatedBy;

	private Date createdDate;

	private Date updatedDate;

	private Long organizationId;

	private String firstPartyIdName;

	private String secondPartyIdName;

	private String fromRelationIdName;
	

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

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
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

	private String toRelationIdName;
}
