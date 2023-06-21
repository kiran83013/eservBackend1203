package com.travel.travtronics.response;

import java.time.LocalDate;
import java.util.Date;

import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
public class PersonQualificationResponse {
	private Long id;

	private Long refId;

	private Long qualificationType;

	private String qualification;

	private String college;

	private String certificationNumber;

	private LocalDate toDate;

	private LocalDate fromDate;

	private Status status;

	private Long createdBy;

	private Long updatedBy;

	private Date createdDate;

	private Date updatedDate;

	private Long organizationId;

	private String qualificationTypeName;
	
	private String businessName;
	
	private String organizationName;

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

	public Long getQualificationType() {
		return qualificationType;
	}

	public void setQualificationType(Long qualificationType) {
		this.qualificationType = qualificationType;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getCertificationNumber() {
		return certificationNumber;
	}

	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
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

	public String getQualificationTypeName() {
		return qualificationTypeName;
	}

	public void setQualificationTypeName(String qualificationTypeName) {
		this.qualificationTypeName = qualificationTypeName;
	}

}
