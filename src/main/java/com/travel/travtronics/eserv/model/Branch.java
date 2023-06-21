package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bank_branch")
@Getter
@Setter
public class Branch extends WhoColumnsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@NotNull(message = "Bank is Mandatory")
	@Column(name = "Bank")
	private Long bank;
	
	@NotBlank(message = "Branch is Mandatory")
	@Column(name = "Branch")
	private String branch;
	
	@Column(name = "BranchAddress", columnDefinition = "LONGTEXT")
	private String branchAddress;
	
	@NotBlank(message = "ShortName is Mandatory")
	@Column(name = "ShortName")
	private String shortName;
	
	@Column(name = "Code")
	private String code;
	
	@Column(name = "Country")
	private Long country;
	
	@Column(name = "Currency")
	private Long currency;
	
	@Column(name = "BranchType")
	private Long brType;
	
	@Column(name = "BranchCategory")
	private Long brCtgy;
	
	@Column(name = "Code1")
	private String code1;
	
	@Column(name = "Code2")
	private String code2;
	
	@Column(name = "Code3")
	private String code3;
	
	@Column(name = "Code4")
	private String code4;
	
	@Column(name = "Code5")
	private String code5;
	
	@NotNull(message = "StartDate is Mandatory")
	@Column(name = "StartDate")
	private Date startDate;
	
	@Column(name = "EndDate")
	private Date endDate;
	
	@Column(name = "Status")
	private Boolean status;

	@Column(name = "OrganizationId")
	private Long organizationId;

}
