package com.travel.travtronics.eserv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travel.travtronics.enums.StatusActive;


@Entity
@Table(name = "sub_module")
public class SubModule extends WhoColumnsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "OrganizationId")
	private Long organizationId;

	@Column(name = "Description")
	private String description;

	@Column(name = "Name")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private StatusActive status;

	@Column(name = "HeaderRelation")
	private String headerRelation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StatusActive getStatus() {
		return status;
	}

	public void setStatus(StatusActive status) {
		this.status = status;
	}

	public String getHeaderRelation() {
		return headerRelation;
	}

	public void setHeaderRelation(String headerRelation) {
		this.headerRelation = headerRelation;
	}

}
