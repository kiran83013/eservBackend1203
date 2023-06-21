package com.travel.travtronics.response;

import com.travel.travtronics.enums.Status;

public class CustomerAddressResponse {
	
	private Long addressId;

    private Long refId;
    
    private String sEmail;
    
	private Long organizationId;
    
   	private Integer customerAddressType;

   	private Integer supplierAddressType;

   	private Boolean regAddress;

   	private String addressName;

   	private String addressDescription;

   	private String primarySupplier;

   	private String billingAndStreet;

   	private String area;

   	private String city;

   	private String state;

   	private String country;

   	private String zipCode;

   	private String landMark;

   	private String geoLocation;

   	private Boolean primaryAddressCustomer;

   	private Boolean primaryAddressSupplier;

   	private Integer primaryContactId;

   	private String email;

   	private String phone1;
   	private String phone2;

   	private Integer createdBy;

   	private Integer updatedBy;

   	private Status status;

   	private Integer wfStatus;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public Integer getCustomerAddressType() {
		return customerAddressType;
	}

	public void setCustomerAddressType(Integer customerAddressType) {
		this.customerAddressType = customerAddressType;
	}

	public Integer getSupplierAddressType() {
		return supplierAddressType;
	}

	public void setSupplierAddressType(Integer supplierAddressType) {
		this.supplierAddressType = supplierAddressType;
	}

	public Boolean getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(Boolean regAddress) {
		this.regAddress = regAddress;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressDescription() {
		return addressDescription;
	}

	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

	public String getPrimarySupplier() {
		return primarySupplier;
	}

	public void setPrimarySupplier(String primarySupplier) {
		this.primarySupplier = primarySupplier;
	}

	public String getBillingAndStreet() {
		return billingAndStreet;
	}

	public void setBillingAndStreet(String billingAndStreet) {
		this.billingAndStreet = billingAndStreet;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	public Boolean getPrimaryAddressCustomer() {
		return primaryAddressCustomer;
	}

	public void setPrimaryAddressCustomer(Boolean primaryAddressCustomer) {
		this.primaryAddressCustomer = primaryAddressCustomer;
	}

	public Boolean getPrimaryAddressSupplier() {
		return primaryAddressSupplier;
	}

	public void setPrimaryAddressSupplier(Boolean primaryAddressSupplier) {
		this.primaryAddressSupplier = primaryAddressSupplier;
	}

	public Integer getPrimaryContactId() {
		return primaryContactId;
	}

	public void setPrimaryContactId(Integer primaryContactId) {
		this.primaryContactId = primaryContactId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getWfStatus() {
		return wfStatus;
	}

	public void setWfStatus(Integer wfStatus) {
		this.wfStatus = wfStatus;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
   	
}
