package com.travel.travtronics.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travel.travtronics.enums.Status;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmpResponse {

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

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long customersId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String customerName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String orgName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String buName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String deptName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String countryName;
}
