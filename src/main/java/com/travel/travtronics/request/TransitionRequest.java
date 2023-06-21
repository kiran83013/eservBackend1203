package com.travel.travtronics.request;

import java.util.Date;

import com.travel.travtronics.enums.StatusActive;

public class TransitionRequest {

	private Long transitionId;

	private Long organization;

	private String deafultStatus;

	private Integer fromStatus;
	
	private Integer toStatus;

	private String moduleName;

	private StatusActive status;

	private Long createdBy;

	private Long updatedBy;

	private Date createdDate;

	private Date updatedDate;

	public Long getTransitionId() {
		return transitionId;
	}

	public void setTransitionId(Long transitionId) {
		this.transitionId = transitionId;
	}

	public Long getOrganization() {
		return organization;
	}

	public void setOrganization(Long organization) {
		this.organization = organization;
	}

	public String getDeafultStatus() {
		return deafultStatus;
	}

	public void setDeafultStatus(String deafultStatus) {
		this.deafultStatus = deafultStatus;
	}

	public Integer getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(Integer fromStatus) {
		this.fromStatus = fromStatus;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public StatusActive getStatus() {
		return status;
	}

	public void setStatus(StatusActive status) {
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

	public Integer getToStatus() {
		return toStatus;
	}

	public void setToStatus(Integer toStatus) {
		this.toStatus = toStatus;
	}

}
