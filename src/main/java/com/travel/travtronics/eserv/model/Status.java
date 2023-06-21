package com.travel.travtronics.eserv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.travel.travtronics.enums.StatusActive;

import lombok.Data;

@Data
@Entity
@Table(name = "status")
public class Status extends WhoColumnsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StatusId")
	private Long statusId;

	@Column(name = "Description")
	private String description;

	@Column(name = "Module")
	private String module;

	@Column(name = "name")
	private String name;

	@Column(name = "Organization")
	private String organization;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private StatusActive status;

	@Column(name = "isOpen")
	private Boolean isOpen;

	@Column(name = "isCancel")
	private Boolean isCancel;

	@Column(name = "isClose")
	private Boolean isClose;

	@Column(name = "isExternal")
	private Boolean isExternal;

	@Column(name = "isCheckList")
	private Boolean isCheckList;

	@JsonRawValue
	@Column(name = "checkList")
	private String checkList;

}
