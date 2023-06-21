package com.travel.travtronics.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BankResponse {
	
	private Long id;
	
	private String name;
	
	private String shortName;
	
	private String code;


	private Long country;

	private Long currency;
	

	private Long bankType;
	

	private Long organizationId;
	
	
	private Long bankCtgy;
	
	private String url;

	private Long biz;

	private String code1;

	private String code2;

	private String code3;

	private String code4;

	private String code5;
	

	private Date startDate;

	private Date endDate;

	private Boolean status;
	
	private Long createdBy;
	
	private Long updatedBy;
	
	private Date createdDate;

	private Date updatedDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String orgName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String countryName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String currencyName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String bankTypeName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String bankCtgyName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String bizName;
	
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getBankTypeName() {
		return bankTypeName;
	}

	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}

	public String getBankCtgyName() {
		return bankCtgyName;
	}

	public void setBankCtgyName(String bankCtgyName) {
		this.bankCtgyName = bankCtgyName;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	
	
}
