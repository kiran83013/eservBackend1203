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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;




@Entity
@Table(name = "bank")
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	@NotBlank(message = "Name is Mandatory")
	@Column(name = "Name")
	private String name;
	@NotBlank(message = "ShortName is Mandatory")
	@Column(name = "ShortName")
	private String shortName;
	@Column(name = "Code")
	private String code;
	@NotNull(message = "Company is Mandatory")
	@Column(name = "Country")
	private Long country;
	@Column(name = "Currency")
	private Long currency;
	@NotNull(message = "BankType is Mandatory")
	@Column(name = "BankType")
	private Long bankType;
	
	@Column(name = "OrganizationId")
	private Long organizationId;
	
	@Column(name = "BankCategory")
	private Long bankCtgy;
	@Column(name = "URL")
	private String url;
	@NotNull(message = "Business is Mandatory")
	@Column(name = "BIZ")
	private Long biz;
	@Column(name = "Code1")
	private String code1;
	@Column(name = "Code2")
	private String code2;
	@Column(name = "Code3")
	private String code3;
	@Column(name = "Code4")
	private String code4;
	@Column(name = "Code5")
	private String code5;
	@NotNull(message = "StartDate is Mandatory")
	@Column(name = "StartDate")
	private Date startDate;
	@Column(name = "EndDate")
	private Date endDate;
	@Column(name = "Status")
	private Boolean status;
	
	@Column(name = "CreatedBy", updatable = false)
	private Long createdBy;
	
	@Column(name = "UpdatedBy")
	private Long updatedBy;
	
	@CreationTimestamp
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getCountry() {
		return country;
	}
	public void setCountry(Long country) {
		this.country = country;
	}
	public Long getCurrency() {
		return currency;
	}
	public void setCurrency(Long currency) {
		this.currency = currency;
	}
	public Long getBankType() {
		return bankType;
	}
	public void setBankType(Long bankType) {
		this.bankType = bankType;
	}
	public Long getBankCtgy() {
		return bankCtgy;
	}
	public void setBankCtgy(Long bankCtgy) {
		this.bankCtgy = bankCtgy;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getBiz() {
		return biz;
	}
	public void setBiz(Long biz) {
		this.biz = biz;
	}
	public String getCode1() {
		return code1;
	}
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public String getCode3() {
		return code3;
	}
	public void setCode3(String code3) {
		this.code3 = code3;
	}
	public String getCode4() {
		return code4;
	}
	public void setCode4(String code4) {
		this.code4 = code4;
	}
	public String getCode5() {
		return code5;
	}
	public void setCode5(String code5) {
		this.code5 = code5;
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
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
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
	
	
	
}
