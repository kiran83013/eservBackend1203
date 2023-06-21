package com.travel.travtronics.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmpVerificationResponse {

	private Long id;

	private Long refId;
	
	private Long type;
	
	private String typeName;
	
	private String businessName;
	
	private String organizationName;

	private Long organizationId;

	private String value;

	private Boolean status;

	private Date fromDate;

	private Date toDate;

	private Long createdBy;

	private Long updatedBy;

	private Date createdDate;
	
	private Date updatedDate;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getRefId() {
		return refId;
	}


	public void setRefId(Long refId) {
		this.refId = refId;
	}


	public Long getType() {
		return type;
	}


	public void setType(Long type) {
		this.type = type;
	}


	public Long getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
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


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
