package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer_contact") // name changed
public class CustomerContactNew extends WhoColumnsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "PrefixID")
	private Long prefix;

	@Column(name = "EmpId")
	private Long empId;

	@Column(name = "CustomerId")
	private Long customerId;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "MiddleName")
	private String middleName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "DesignationId")
	private Long designationId;

	@Column(name = "DesignationName")
	private String designationName;

	@Column(name = "RoleId")
	private Long roleId;

	@Column(name = "PrimaryEmail")
	private String primaryEmail;

	@Column(name = "PrimaryCCP")
	private Long primaryCCP;

	@Column(name = "PrimaryPhoneNumber")
	private Long primaryPhoneNumber;

	@Column(name = "SecondaryEmail")
	private String secondaryEmail;

	@Column(name = "SecondaryCCP")
	private Long secondaryCCP;

	@Column(name = "SecondaryPhoneNumber")
	private Long secondaryPhoneNumber;

	@Column(name = "TelephoneNumber")
	private Long telephoneNumber;

	@Column(name = "RemarksAndNotes")
	private String remarksAndNotes;

	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "DateOfBirth")
	private Date dob;

	@Column(name = "Passport")
	private String passport;

	@Column(name = "Nationality")
	private Long nationality;

	@Column(name = "OrganizationId")
	private Long organizationId;

	@Column(name = "IssuedCountry")
	private Long issuedCountry;

//	@Column(name = "CreatedBy")
//	private Long createdBy;
//
//	@Column(name = "UpdatedBy")
//	private Long updatedBy;
//
//	@Column(name = "CreatedDate")
//	private Date createdDate;
//
//	@Column(name = "UpdatedDate")
//	private Date updatedDate;

	@Column(name = "ImageUrl")
	private String dpImageUrl;

	@Column(name = "Status")
	private Boolean status;

	@Column(name = "PersonId")
	private Long personId;

}
