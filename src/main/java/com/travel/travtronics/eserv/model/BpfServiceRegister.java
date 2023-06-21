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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.enums.StatusActive;

import lombok.Data;


@Data
@Entity
@Table(name ="bpf_service_register")
public class BpfServiceRegister {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID" )
	private Long id;
	
	@Column(name = "ServiceName")
	private String serviceName;
	
	@Column(name = "ServiceUrl")
	private String serviceUrl;
	
	@Column(name = "ModuleId")
	private Long moduleId;
	
	@Column(name = "OrganizationId")
	private Long organizationId;
	
	
	@Column(name = "StartDate")
	private Date startDate; 
	
	@Column(name = "EndDate")
	private Date endDate;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@CreationTimestamp
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private String updatedBy;

	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;
	
	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private StatusActive status;
	
	@Column(name = "Description",columnDefinition = "LONGTEXT")
	private String description;
	
	@Column(name = "IsQuartz")
	private Boolean isQuartz;
	
	@Column(name = "Output")
	private String output;
	
	@Column(name = "Template")
	private String template;
	
	

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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
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

	public StatusActive getStatus() {
		return status;
	}

	public void setStatus(StatusActive status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}
