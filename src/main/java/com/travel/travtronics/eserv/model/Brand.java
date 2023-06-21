package com.travel.travtronics.eserv.model;


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
@Table(name = "customer_brand")
public class Brand extends WhoColumnsModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CustomerId")
	private Long  customerId;
	
	@Column(name = "OrganizationId")
	private Long  organizationId;

	@Column(name = "Name")
	private String name;
	
	@Column(name = "ShortName")
	private String shortName;
	
	@Column(name = "Code")
	private String code;
	
	@Column(name = "Category")
	private Long category;
	
	@Column(name = "Type")
	private Long type;
	
	@Column(name = "Summary")
	private String summary;
	
	@Column(name="Description",columnDefinition="LONGTEXT")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
