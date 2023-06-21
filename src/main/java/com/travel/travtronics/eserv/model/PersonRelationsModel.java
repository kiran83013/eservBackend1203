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

@Data
@Entity
@Table(name = "person_relationships")
public class PersonRelationsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RELATIONSHIPID")
	private Long relationShipId;

	@Column(name = "FirstPartyID")
	private Long firstPartyId;

	@Column(name = "SecondPartyID")
	private Long secondPartyId;

	@Column(name = "SecondPartyTypeID")
	private String secondPartyTypeId;

	@Column(name = "FromRelationID")
	private Long fromRelationId;

	@Column(name = "ToRelationID")
	private Long toRelationId;

	@Column(name = "FromDate")
	private LocalDate fromDate;

	@Column(name = "ToDate")
	private LocalDate toDate;

	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "CreatedBy", updatable = false)
	private Long createdBy;

	@Column(name = "UpdatedBy")
	private Long updatedBy;

	@Column(name = "CreatedDate", updatable = false)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "UpdatedDate")
	@UpdateTimestamp
	private Date updatedDate;

	@Column(name = "OrganizationId")
	private Long organizationId;
}
