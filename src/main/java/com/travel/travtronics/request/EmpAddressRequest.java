package com.travel.travtronics.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.travel.travtronics.enums.Status;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmpAddressRequest {
	
	@NotNull
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

	private Date createdDate;

	private Date updatedDate;

	private Integer createdBy;

	private Integer updatedBy;

	private Status status;

	private Integer wfStatus;
}
