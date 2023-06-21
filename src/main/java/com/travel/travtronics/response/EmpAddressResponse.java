package com.travel.travtronics.response;

import com.travel.travtronics.enums.Status;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmpAddressResponse {
	
	private Long addressId;

    private Long refId;
    
    private Long organizationId;
    
    private String sEmail;
    
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
}
