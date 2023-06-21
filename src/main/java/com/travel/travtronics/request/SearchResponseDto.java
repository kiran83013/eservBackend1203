package com.travel.travtronics.request;

import java.util.Date;

import com.travel.travtronics.eserv.model.Status;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SearchResponseDto {
	
	private Long id;
	private Long businessUnitId;
	private String businessUnitName;
	private Long departmentId;
	private String departmentName;
	private Long createdBy;
	private Date createdDate;
	private Long designationId;
	private String designationName;
	private Date dateOfBirth;
	private String imageUrl;
	private Date endDate;
	private String firstName;
	private Long issuedCountry;
	private String issuedCountryName;
	private String middleName;
	private String lastName;
	private Long nationality;
	private Long organizationId;
	private String organizationName;
	private String passport;
	private Long prefix;
	private Long primaryCCP;
	private String primaryEmail;
	private Long primaryPhoneNumber;
	private String remarksAndNotes;
	private Long secondaryCCP;
	private String secondaryEmail;
	private Long secondaryPhoneNumber;
	private Date startDate;
	private String status;
	private Long telephoneNumber;
	private Long updatedBy;
	private Date updatedDate;
	private Long customersId;
    private String businessName;
    
    private Long contactId;
	private Boolean contactStatus;
}
