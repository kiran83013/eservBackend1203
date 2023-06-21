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

import lombok.Data;

@Entity
@Table(name = "person_social")
@Data
public class PersonSocialModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "RefId")
	private Long refId;

	@Column(name = "Type")
	private Long type;

	@Column(name = "Value")
	private String value;

	@Column(name = "FromDate")
	private LocalDate fromDate;

	@Column(name = "ToDate")
	private LocalDate toDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private com.travel.travtronics.enums.Status status;

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
