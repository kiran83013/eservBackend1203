package com.travel.travtronics.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travel.travtronics.converter.IntegerListConverter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerActivityResponse {
	private Long id;

	private Long activityId;

	private Long organizationId;

	@Convert(converter = IntegerListConverter.class)
	private List<Integer> subActivityId;

	private Long customerId;

	private Long segementId;

	private LocalDate startDate;

	private LocalDate endDate;

	private com.travel.travtronics.enums.Status status;

	private Integer createdBy;

	private Date createdDate;

	private Integer updatedBy;

	private Date updatedDate;

	private String subActivityIdName;
	
	private String activityName;

	private String customerIdName;

	private String segementIdName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public List<Integer> getSubActivityId() {
		return subActivityId;
	}

	public void setSubActivityId(List<Integer> subActivityId) {
		this.subActivityId = subActivityId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSegementId() {
		return segementId;
	}

	public void setSegementId(Long segementId) {
		this.segementId = segementId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public com.travel.travtronics.enums.Status getStatus() {
		return status;
	}

	public void setStatus(com.travel.travtronics.enums.Status status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getSubActivityIdName() {
		return subActivityIdName;
	}

	public void setSubActivityIdName(String subActivityIdName) {
		this.subActivityIdName = subActivityIdName;
	}


	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getCustomerIdName() {
		return customerIdName;
	}

	public void setCustomerIdName(String customerIdName) {
		this.customerIdName = customerIdName;
	}

	public String getSegementIdName() {
		return segementIdName;
	}

	public void setSegementIdName(String segementIdName) {
		this.segementIdName = segementIdName;
	}
	
}
