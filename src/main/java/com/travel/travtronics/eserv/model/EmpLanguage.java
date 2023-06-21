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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.enums.Status;

import lombok.Data;


@Data
@Entity
@Table(name = "emp_language")
public class EmpLanguage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@NotNull(message = "RefId Should Not Be Empty or Zero")
	@Column(name = "RefId")
	private Long refId;
	
	@Column(name = "OrganizationId",nullable = false)
	private Long organizationId;

	@NotNull(message = "LanguageId Should Not Be Empty or Zero")
	@Column(name = "LanguageId")
	private Long languageId;

	@Column(name = "LanguageRead")
	private Boolean read;

	@Column(name = "LanguageWrite")
	private Boolean write;

	@Column(name = "Speak")
	private Boolean speak;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;

	@NotNull(message = "CreatedBy Should Not Be Empty or Zero")
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
}
