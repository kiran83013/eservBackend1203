package com.travel.travtronics.eserv.model;

import java.time.LocalDate;
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

@Entity
@Table(name = "person_qualification")
@Data
public class PersonQualificationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "RefId")
	private Long refId;

	@Column(name = "QualificationType")
	private Long qualificationType;

	@Column(name = "Qualification")
	private String qualification;

	@Column(name = "College")
	private String college;

	@Column(name = "CertificationNumber")
	private String certificationNumber;

	@Column(name = "ToDate")
	private LocalDate toDate;

	@Column(name = "FromDate")
	private LocalDate fromDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;

	@Column(name = "CreatedBy", updatable = false)
	private Long createdBy;

	@Column(name = "UpdatedBy")
	private Long updatedBy;

	@CreationTimestamp
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "OrganizationId")
	private Long organizationId;

}
