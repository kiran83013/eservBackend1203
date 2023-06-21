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

import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
@Entity
@Table(name = "emp_address")
public class EmpAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESSID",columnDefinition = "int")
	private Long addressId;
	
	@Column(name = "REFID",columnDefinition = "int")
	private Long refId;
	
	@Column(name = "OrganizationId")
	private Long organizationId;
	
	@Column(name = "Customer_AddressType")
	private Integer customerAddressType;

	@Column(name = "Supplier_AddressType")
	private Integer supplierAddressType;

	
	@Column(name ="Reg_Address")
	private Boolean  regAddress;
	
	@Column(name ="Primary_Supplier")
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

	@Column(name = "CreatedBy", columnDefinition = "int", updatable = false)
	private Integer createdBy;

	@Column(name = "UpdatedBy", columnDefinition = "int")
	private Integer updatedBy;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;

	
}
