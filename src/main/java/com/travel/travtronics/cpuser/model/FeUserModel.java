package com.travel.travtronics.cpuser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "a3m_account", uniqueConstraints = { @UniqueConstraint(columnNames = "email", name = "emailkey"),
		@UniqueConstraint(columnNames = "phone_primary", name = "phonekey") })
public class FeUserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer userId;

	@JsonIgnore
	@Column(name = "iamId", updatable = false)
	private String iamId;

	@JsonProperty("contactId")
	@Column(name = "contact_id")
	private String employeeId;

	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "username")
	private String userName;

	@Email(message = "Email is not valid")
	@Column(name = "email", updatable = false)
	private String email;

	@Column(name = "email_secondary")
	private String emailSecondary;

	@Column(name = "phone_primary", updatable = false)
	private String phoneNumber;

	@Column(name = "phone_secondary")
	private String phoneSecondary;

	/*
	 * @Column(name = "first_name") private String firstName;
	 * 
	 * @Column(name = "last_name") private String lastName;
	 */

	@Column(name = "createdon", updatable = false)
	private String createdDate;

	@Column(name = "verifiedon")
	private String verifiedOn;

	@Column(name = "lastsignedinon")
	private String lastSignedInOn;

	@Column(name = "resetsenton")
	private String resetSentOn;

	@Column(name = "deletedon")
	private String deletedOn;

	@Column(name = "suspendedon")
	private String suspendedOn;

	@Column(name = "employee_booking_amount")
	private Double employeeBookingAmount;

	@Column(name = "credit_limit_amount")
	private Double creditLimitAmount;

	@Column(name = "running_balance")
	private Double runningBalance;

	@Column(name = "userupdatepassword")
	private Integer userUpdatePassword;

	@Column(name = "status")
	private String status;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "passwordvalid")
	private String passwordValid;

	@Column(name = "person_id")
	private Integer personId;

	@Column(name = "biz_id")
	private Integer bizId;
	
	



	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public String getPasswordValid() {
		return passwordValid;
	}

	public void setPasswordValid(String passwordValid) {
		this.passwordValid = passwordValid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIamId() {
		return iamId;
	}

	public void setIamId(String iamId) {
		this.iamId = iamId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailSecondary() {
		return emailSecondary;
	}

	public void setEmailSecondary(String emailSecondary) {
		this.emailSecondary = emailSecondary;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneSecondary() {
		return phoneSecondary;
	}

	public void setPhoneSecondary(String phoneSecondary) {
		this.phoneSecondary = phoneSecondary;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getVerifiedOn() {
		return verifiedOn;
	}

	public void setVerifiedOn(String verifiedOn) {
		this.verifiedOn = verifiedOn;
	}

	public String getLastSignedInOn() {
		return lastSignedInOn;
	}

	public void setLastSignedInOn(String lastSignedInOn) {
		this.lastSignedInOn = lastSignedInOn;
	}

	public String getResetSentOn() {
		return resetSentOn;
	}

	public void setResetSentOn(String resetSentOn) {
		this.resetSentOn = resetSentOn;
	}

	public String getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(String deletedOn) {
		this.deletedOn = deletedOn;
	}

	public String getSuspendedOn() {
		return suspendedOn;
	}

	public void setSuspendedOn(String suspendedOn) {
		this.suspendedOn = suspendedOn;
	}

	public Double getEmployeeBookingAmount() {
		return employeeBookingAmount;
	}

	public void setEmployeeBookingAmount(Double employeeBookingAmount) {
		this.employeeBookingAmount = employeeBookingAmount;
	}

	public Double getCreditLimitAmount() {
		return creditLimitAmount;
	}

	public void setCreditLimitAmount(Double creditLimitAmount) {
		this.creditLimitAmount = creditLimitAmount;
	}

	public Double getRunningBalance() {
		return runningBalance;
	}

	public void setRunningBalance(Double runningBalance) {
		this.runningBalance = runningBalance;
	}

	public Integer getUserUpdatePassword() {
		return userUpdatePassword;
	}

	public void setUserUpdatePassword(Integer userUpdatePassword) {
		this.userUpdatePassword = userUpdatePassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
