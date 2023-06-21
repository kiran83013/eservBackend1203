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
@Table(name = "module")
public class Modules extends WhoColumnsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ModuleId")
	private Long moduleId;

	@Column(name = "Description")
	private String description;

	@Column(name = "ModuleName")
	private String moduleName;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private StatusActive status;

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public StatusActive getStatus() {
		return status;
	}

	public void setStatus(StatusActive status) {
		this.status = status;
	}

}
