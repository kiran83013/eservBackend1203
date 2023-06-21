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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.travel.travtronics.enums.Status;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "emp_profile")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "Prefix")
	private Long prefix;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "MiddleName")
	private String middleName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "OrganizationId")
	private Long organizationId;

	@Column(name = "DepartmentId")
	private Long departmentId;

	@Column(name = "BusinessUnitId")
	private Long businessUnitId;

	@Column(name = "DesignationId")
	private Long designationId;

	@Column(name = "DesignationName")
	private String designationName;

	@Column(name = "PrimaryEmail", unique = true)
	private String primaryEmail;

	@Column(name = "PrimaryPhoneNumber", unique = true)
	private Long primaryPhoneNumber;

	@Column(name = "SecondaryEmail")
	private String secondaryEmail;

	@Column(name = "SecondaryPhoneNumber")
	private Long secondaryPhoneNumber;

	@Column(name = "TelephoneNumber")
	private Long telephoneNumber;

	@Column(name = "Nationality")
	private Long nationality;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
	@Column(name = "DateOfBirth")
	private Date dob;

	@Column(name = "Passport")
	private String passport;

	@Column(name = "IssuedCountry")
	private Long issuedCountry;

	@Column(name = "RemarksAndNotes", columnDefinition = "LONGTEXT")
	private String remarksAndNotes;

	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "ImageUrl")
	private String dpImageUrl;

	@Column(name = "CreatedBy", updatable = false)
	private Integer createdBy;

	@Column(name = "UpdatedBy")
	private Integer updatedBy;

	@CreationTimestamp
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;

	@Column(name = "PrimaryCcp")
	private Long primaryCcp;

	@Column(name = "SecondaryCcp")
	private Long secondaryCcp;

	@Column(name = "CustomersId")
	private Long customersId;

}
