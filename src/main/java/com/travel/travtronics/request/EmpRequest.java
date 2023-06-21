package com.travel.travtronics.request;

import java.util.Date;

import com.travel.travtronics.enums.Status;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmpRequest {

	private Long id;

	private Long prefix;

	private String firstName;

	private String middleName;

	private String lastName;

	private Long organizationId;

	private Long departmentId;

	private Long businessUnitId;

	private Long designationId;

	private String designationName;

	private String primaryEmail;

	private Long primaryPhoneNumber;

	private String secondaryEmail;

	private Long secondaryPhoneNumber;

	private Long telephoneNumber;

	private Long nationality;

	

	private Date dob;

	private String passport;

	private Long issuedCountry;

	

	private String remarksAndNotes;

	private Date startDate;

	private Date endDate;

	private String dpImageUrl;

	private Integer createdBy;

	private Integer updatedBy;

	private Date createdDate;

	private Date updatedDate;

	private Status status;

	private Long primaryCcp;

	private Long secondaryCcp;

	private Long customersId;

	
}
