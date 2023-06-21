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

import com.travel.travtronics.enums.StatusActive;

import lombok.Data;

@Data
@Entity
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TeamId")
	private Long teamId;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "DepartmentId")
	private Long departmentId;

	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "ModuleId")
	private Long moduleId;

	@Column(name = "OrganizationId")
	private Long organizationId;

	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "businessUnit")
	private Long businessUnit;

	@Column(name = "teamEmail")
	private String teamEmail;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private StatusActive status;

	@Column(name = "TeamName", unique = true)
	private String teamName;

	@Column(name = "UpDatedBy")
	private String upDatedBy;

	@Column(name = "UpDatedDate")
	private Date upDatedDate;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public StatusActive getStatus() {
		return status;
	}

	public void setStatus(StatusActive status) {
		this.status = status;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getUpDatedBy() {
		return upDatedBy;
	}

	public void setUpDatedBy(String upDatedBy) {
		this.upDatedBy = upDatedBy;
	}

	public Date getUpDatedDate() {
		return upDatedDate;
	}

	public void setUpDatedDate(Date upDatedDate) {
		this.upDatedDate = upDatedDate;
	}

	public Long getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(Long businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getTeamEmail() {
		return teamEmail;
	}

	public void setTeamEmail(String teamEmail) {
		this.teamEmail = teamEmail;
	}

}
