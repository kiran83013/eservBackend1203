package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import com.travel.travtronics.util.QueryConst;

import lombok.Getter;

@Immutable
@Subselect(value = QueryConst.GET_CONTACT_SEARCH_INFO)
@Entity
@Getter

public class CustomerSearchEntity {
	
	
	@Id
	@Column(name = "ID", insertable = false, updatable = false)
	private Long id;

	@Column(name = "PrefixID", insertable = false, updatable = false)
	private Long prefix;

	@Column(name = "EmpId", insertable = false, updatable = false)
	private Long empId;

	@Column(name = "CustomerId", insertable = false, updatable = false)
	private Long customerId;

	@Column(name = "FirstName", insertable = false, updatable = false)
	private String firstName;

	@Column(name = "MiddleName", insertable = false, updatable = false)
	private String middleName;

	@Column(name = "LastName", insertable = false, updatable = false)
	private String lastName;

	@Column(name = "DesignationId", insertable = false, updatable = false)
	private Long designationId;

	@Column(name = "DesignationName", insertable = false, updatable = false)
	private String designationName;

	@Column(name = "RoleId", insertable = false, updatable = false)
	private Long roleId;

	@Column(name = "PrimaryEmail", insertable = false, updatable = false)
	private String primaryEmail;

	@Column(name = "PrimaryCCP", insertable = false, updatable = false)
	private Long primaryCCP;

	@Column(name = "PrimaryPhoneNumber", insertable = false, updatable = false)
	private Long primaryPhoneNumber;

	@Column(name = "SecondaryEmail", insertable = false, updatable = false)
	private String secondaryEmail;

	@Column(name = "SecondaryCCP", insertable = false, updatable = false)
	private Long secondaryCCP;

	@Column(name = "SecondaryPhoneNumber", insertable = false, updatable = false)
	private Long secondaryPhoneNumber;

	@Column(name = "TelephoneNumber", insertable = false, updatable = false)
	private Long telephoneNumber;

	@Column(name = "RemarksAndNotes", insertable = false, updatable = false)
	private String remarksAndNotes;

	@Column(name = "StartDate", insertable = false, updatable = false)
	private Date startDate;

	@Column(name = "EndDate", insertable = false, updatable = false)
	private Date endDate;

	@Column(name = "DateOfBirth", insertable = false, updatable = false)
	private Date dob;

	@Column(name = "Passport", insertable = false, updatable = false)
	private String passport;

	@Column(name = "Nationality", insertable = false, updatable = false)
	private Long nationality;

	@Column(name = "OrganizationId", insertable = false, updatable = false)
	private Long organizationId;

	@Column(name = "IssuedCountry", insertable = false, updatable = false)
	private Long issuedCountry;

	@Column(name = "ImageUrl", insertable = false, updatable = false)
	private String dpImageUrl;

	@Column(name = "Status", insertable = false, updatable = false)
	private Boolean status;

	@Column(name = "PersonId", insertable = false, updatable = false)
	private Long personId;

	@Column(name = "CreatedBy", insertable = false, updatable = false)
	private Long createdBy;
	@Column(name = "UpdatedBy", insertable = false, updatable = false)
	private Long updatedBy;

	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;

	@Column(name = "UpdatedDate", insertable = false, updatable = false)
	private Date updatedDate;
	
	@Column(name="customer")
	private String customer;

}
