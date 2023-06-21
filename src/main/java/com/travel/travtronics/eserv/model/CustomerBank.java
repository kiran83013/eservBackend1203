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

import com.travel.travtronics.enums.Status;

@Table(name = "customer_bank")
@Entity
public class CustomerBank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "BankName")
	private String bankName;

	@Column(name = "BranchId")
	private Long branchId;
	
	@Column(name = "OrganizationId")
	private Long organizationId;

	@Column(name = "BranchName")
	private String branchName;

	@Column(name = "BranchAddress")
	private String branchAddress;

	@Column(name = "BankAccountType")
	private String bankAccountType;

	@Column(name = "BankAccountName")
	private String bankAccountName;

	@Column(name = "BankAccountNumber")
	private String bankAccountNumber;

	@Column(name = "ChequeDigits")
	private String chequeDigits;

	@Column(name = "Currency")
	private String currency;

	@Column(name = "SwiftCode")
	private String swiftCode;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "FileUrl")
	private String fileUrl;

	@Column(name = "CreatedBy",updatable = false)
	private Long createdBy;

	@Column(name = "UpdatedBy")
	private Long updatedBy;
	
	@CreationTimestamp
	@Column(name = "CreatedDate",updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "BankId")
	private Long bankId;

	@Column(name = "RefId")
	private Long refId;
	

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getChequeDigits() {
		return chequeDigits;
	}

	public void setChequeDigits(String chequeDigits) {
		this.chequeDigits = chequeDigits;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}
	
	
}
