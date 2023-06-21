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

import lombok.Data;


@Data
@Table(name = "emp_bank")
@Entity
public class EmpBank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "RefId")
	private Long refId;
	
	@Column(name = "BankId")
	private Long bankId;
	
	@Column(name = "OrganizationId")
	private Long organizationId;
	
	@Column(name = "BankName")
	private String bankName;

	@Column(name = "BranchId")
	private Long branchId;

	
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
}
