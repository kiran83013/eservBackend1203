package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emp_pid")
public class EmpPid extends WhoColumnsModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull(message = "Ref Should Not Be Null")
	@Column(name = "RefId")
	private Long refId;

	@NotNull(message = "Type Should Not Be Null")
	@Column(name = "Type")
	private Long type;
	
	@Column(name = "organizationId")
	private Long organizationId;

	@NotBlank(message = "Value is Mandatory")
	@Column(name = "Value")
	private String value;

	@NotNull(message = "FromDate is Mandatory")
	@Column(name = "FromDate")
	private Date fromDate;

	@NotNull(message = "ToDate is Mandatory")
	@Column(name = "ToDate")
	private Date toDate;

	@Column(name = "Status")
	private Boolean status;

//	@Column(name = "CreatedBy")
//	private Long createdBy;
//
//	@Column(name = "UpdatedBy")
//	private Long updatedBy;
//
//	@Column(name = "CreatedDate")
//	private Date createdDate;
//
//	@Column(name = "UpdatedDate")
//	private Date updatedDate;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "Url")
	private String url;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	
	
}
