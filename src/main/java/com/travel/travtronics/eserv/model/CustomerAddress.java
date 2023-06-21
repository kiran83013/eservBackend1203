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
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.enums.Status;
import com.travel.travtronics.response.CustomerSearchInfoResponse;

@Entity
@Table(name = "customer_address")

@SqlResultSetMappings({
	@SqlResultSetMapping(name = "business_information_search", classes = @ConstructorResult(targetClass = CustomerSearchInfoResponse.class, columns = {
			@ColumnResult(name = "customerId", type = Long.class),
			@ColumnResult(name = "businessName", type = String.class),
			@ColumnResult(name = "shortName", type = String.class),
			@ColumnResult(name = "type", type = Integer.class),
			@ColumnResult(name = "typeName", type = String.class),
			@ColumnResult(name = "code", type = String.class),
			@ColumnResult(name = "category", type = Integer.class),
			@ColumnResult(name = "categoryName", type = String.class),
			@ColumnResult(name = "industry", type = Integer.class),
			@ColumnResult(name = "industryName", type = String.class),
			@ColumnResult(name = "subIndustry", type = Integer.class),
			@ColumnResult(name = "subIndustryName", type = String.class),
			@ColumnResult(name = "startDate", type = Date.class),
			@ColumnResult(name = "endDate", type = Date.class),
			@ColumnResult(name = "isCustomer", type = Boolean.class),
			@ColumnResult(name = "custRegistrationNumber", type = Integer.class),
			@ColumnResult(name = "isSupplier", type = Boolean.class),
			@ColumnResult(name = "supplRegistrationNumber", type = Integer.class),
			@ColumnResult(name = "rating", type = Integer.class),
			@ColumnResult(name = "ratingName", type = String.class),
			@ColumnResult(name = "legacyID", type = String.class),
			@ColumnResult(name = "wfStatus", type = Integer.class),
			@ColumnResult(name = "status", type = String.class),
			@ColumnResult(name = "createdDate", type = Date.class),
			@ColumnResult(name = "updatedDate", type = Date.class),
			@ColumnResult(name = "createdBy", type = Integer.class),
			@ColumnResult(name = "updatedBy", type = Integer.class),
			@ColumnResult(name = "businessUnitId", type = Long.class),
			@ColumnResult(name = "organizationId", type = Long.class),
			@ColumnResult(name = "locationId", type = Long.class),
			@ColumnResult(name = "costCenterId", type = Long.class),
			@ColumnResult(name = "businessUnitName", type = String.class),
			@ColumnResult(name = "organizationName", type = String.class),
			@ColumnResult(name = "locationName", type = String.class),
			@ColumnResult(name = "costCenterName", type = String.class) })) })
public class CustomerAddress {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESSID", columnDefinition = "int")
	private Long addressId;

	@Column(name = "REFID", columnDefinition = "int")
	private Long refId;
	
	@Column(name = "OrganizationId")
	private Long organizationId;
	
	@Column(name = "Customer_AddressType")
	private Integer customerAddressType;

	@Column(name = "Supplier_AddressType")
	private Integer supplierAddressType;

	@Column(name = "Reg_Address")
	private Boolean  regAddress;

	@Column(name = "Primary_Supplier")
	private String primarySupplier;

	@Column(name = "BillingAndStreet")
	private String billingAndStreet;

	@Column(name = "Area", length = 100)
	private String area;

	@Column(name = "City", length = 100)
	private String city;

	@Column(name = "State", length = 50)
	private String state;

	@Column(name = "Country", length = 50)
	private String country;

	@Column(name = "ZipCode", length = 20)
	private String zipCode;

	@Column(name = "LandMark", length = 100)
	private String landMark;

	@Column(name = "GeoLocation")
	private String geoLocation;

	@Column(name = "PrimaryContactId")
	private Integer primaryContactId;

	@Column(name = "WfStatus")
	private Integer wfStatus;

	@Column(name = "AddressName", length = 100)
	private String addressName;

	@Column(name = "AddressDescription")
	private String addressDescription;

	@Column(name="Email",length = 100)
	private String email;
	
	@Column(name="SEmail")
	private String sEmail;
	
	
	@Column(name="Phone1",length = 50)
	private String phone1;
	
	
	@Column(name="Phone2",length =50)
	private String phone2;

	@Column(name="Primary_Address_Customer")
	private Boolean primaryAddressCustomer;
	
	@Column(name="Primary_Address_Supplier")
	private Boolean primaryAddressSupplier;

	@CreationTimestamp
	@Column(name = "CreatedDate",updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "CreatedBy", columnDefinition = "int",updatable = false)
	private Integer createdBy;

	@Column(name = "UpdatedBy", columnDefinition = "int")
	private Integer updatedBy;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;
	
	

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

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

	public Integer getPrimaryContactId() {
		return primaryContactId;
	}

	public void setPrimaryContactId(Integer primaryContactId) {
		this.primaryContactId = primaryContactId;
	}

	public Integer getWfStatus() {
		return wfStatus;
	}

	public void setWfStatus(Integer wfStatus) {
		this.wfStatus = wfStatus;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
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
	
	

}
