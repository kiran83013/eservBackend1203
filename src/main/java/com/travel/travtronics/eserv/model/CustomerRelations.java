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

import com.travel.travtronics.enums.Status;

@Entity
@Table(name = "customer_relations")
public class CustomerRelations extends WhoColumnsModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RELATIONSHIPID")
	private Long relationShipId;
	
	@Column(name = "FirstPartyID")
	private Long firstPartyId;
	
	@Column(name = "OrganizationId")
	private Long organizationId;
	
	@Column(name = "SecondPartyTypeID")
	private String secondPartyTypeId;

	@Column(name = "SecondPartyID")
	private Long secondPartyId;
	
	@Column(name = "FromRelationID")
	private Long fromRelationId;
	
	@Column(name = "ToRelationID")
	private Long toRelationId;
	
	@Column(name = "FromDate")
	private Date fromDate;

	@Column(name = "ToDate")
	private Date toDate;

	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private Status status;

//	@Column(name = "CreatedBy")
//	private Long createdBy;
//	
//	@Column(name = "UpdatedBy")
//	private Long updatedBy;
//	
//	@Column(name = "CreatedDate")
//	@CreationTimestamp
//	private Date createdDate;
//	
//	@Column(name = "UpdatedDate")
//	@UpdateTimestamp
//	private Date updatedDate;

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

	public String getSecondPartyTypeId() {
		return secondPartyTypeId;
	}

	public void setSecondPartyTypeId(String secondPartyTypeId) {
		this.secondPartyTypeId = secondPartyTypeId;
	}

	public Long getSecondPartyId() {
		return secondPartyId;
	}

	public void setSecondPartyId(Long secondPartyId) {
		this.secondPartyId = secondPartyId;
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	

//	public Long getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(Long createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public Long getUpdatedBy() {
//		return updatedBy;
//	}
//
//	public void setUpdatedBy(Long updatedBy) {
//		this.updatedBy = updatedBy;
//	}
//
//	public Date getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public Date getUpdatedDate() {
//		return updatedDate;
//	}
//
//	public void setUpdatedDate(Date updatedDate) {
//		this.updatedDate = updatedDate;
//	}
	
	
}
