package com.travel.travtronics.response;

import java.util.Date;

import com.travel.travtronics.enums.Status;

import lombok.Data;


@Data
public class EmpBankResponse {
	
	private Long id;

	private String bankName;

	private Long branchId;
	
	private Long organizationId;

	private String branchName;

	private String branchAddress;

	private String bankAccountType;

	private String bankAccountName;

	private String bankAccountNumber;

	private String chequeDigits;

	private String currency;

	private String swiftCode;

	private String notes;

	private String fileUrl;

	private Long createdBy;

	private Long updatedBy;

	private Date createdDate;

	private Date updatedDate;

	private Status status;

	private Long bankId;

	private Long refId;

	private String bankAccountTypeName;

	private String currencyName;

}
