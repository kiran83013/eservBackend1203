package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pax_profile")
public class Pax {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	// @NotNull(message = "Prefix Should Not Be Null")
	@Column(name = "Prefix")
	private Long prefix;

	@Column(name = "CustomerId")
	private Long customerId;

	// @NotBlank(message = "First Name is Mandatory")
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
	private String secondaryPhoneNumber;

	@Column(name = "TelephoneNumber")
	private Long telephoneNumber;

	@Column(name = "RemarksAndNotes", columnDefinition = "LONGTEXT")
	private String remarksAndNotes;

	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "Status")
	private Boolean status;

	@Column(name = "DateOfBirth")
	private Date dob;

	@Column(name = "Passport")
	private String passport;

	@Column(name = "Nationality")
	private Long nationality;

	@Column(name = "IssuedCountry")
	private Long issuedCountry;

	@Column(name = "CreatedBy")
	private Integer createdBy;

	@Column(name = "UpdatedBy")
	private Integer updatedBy;

	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "ImageUrl")
	private String dpImageUrl;
	
	
	

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrefix() {
		return prefix;
	}

	public void setPrefix(Long prefix) {
		this.prefix = prefix;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public Long getPrimaryCCP() {
		return primaryCCP;
	}

	public void setPrimaryCCP(Long primaryCCP) {
		this.primaryCCP = primaryCCP;
	}

	public Long getPrimaryPhoneNumber() {
		return primaryPhoneNumber;
	}

	public void setPrimaryPhoneNumber(Long primaryPhoneNumber) {
		this.primaryPhoneNumber = primaryPhoneNumber;
	}

	

	public Long getSecondaryCCP() {
		return secondaryCCP;
	}

	public void setSecondaryCCP(Long secondaryCCP) {
		this.secondaryCCP = secondaryCCP;
	}

	public String getSecondaryPhoneNumber() {
		return secondaryPhoneNumber;
	}

	public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}

	public Long getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(Long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getRemarksAndNotes() {
		return remarksAndNotes;
	}

	public void setRemarksAndNotes(String remarksAndNotes) {
		this.remarksAndNotes = remarksAndNotes;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Long getNationality() {
		return nationality;
	}

	public void setNationality(Long nationality) {
		this.nationality = nationality;
	}

	public Long getIssuedCountry() {
		return issuedCountry;
	}

	public void setIssuedCountry(Long issuedCountry) {
		this.issuedCountry = issuedCountry;
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

	public String getDpImageUrl() {
		return dpImageUrl;
	}

	public void setDpImageUrl(String dpImageUrl) {
		this.dpImageUrl = dpImageUrl;
	}

	
	
	
	

	

}
