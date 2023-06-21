package com.travel.travtronics.eserv.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.converter.IntegerListConverter;

@Entity
@Table(name = "customer_activity")
public class CustomerActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ActivityId")
	private Long activityId;
	
	@Column(name = "OrganizationId")
	private Long organizationId;

	@Convert(converter = IntegerListConverter.class)
	@Column(name = "SubActivityId")
	private List<Integer> subActivityId;

	@Column(name = "CustomerId")
	private Long customerId;

	@Column(name = "SegementId")
	private Long segementId;

	@Column(name = "StartDate")
	private LocalDate startDate;

	@Column(name = "EndDate")
	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private com.travel.travtronics.enums.Status status;

	@Column(name = "CreatedBy", updatable = false)
	private Integer createdBy;

	@CreationTimestamp
	@Column(name = "createdDate", updatable = false)
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private Integer updatedBy;

	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;
	
	

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public List<Integer> getSubActivityId() {
		return subActivityId;
	}

	public void setSubActivityId(List<Integer> subActivityId) {
		this.subActivityId = subActivityId;
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

}
