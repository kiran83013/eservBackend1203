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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.enums.StatusActive;

import lombok.Data;

@Data
@Entity
@Table(name ="member_to_team")
public class MembertoTeam {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MtoTId")
	private Long mtoTId;
	
	@Column(name="OrganizationId")
	private Long organizationId;
	
	@Column(name = "EndDate")
	private Date endDate;
	
	@Column(name ="UserName")
	private String userName;
	
	@Column(name = "StartDate")
	private Date startDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private StatusActive status;
	
	@Column(name = "TeamId")
	private  Long  teamId;
	
	@Column(name = "CreatedBy",updatable = false)
	private String createdBy;
	
	@CreationTimestamp
	@Column(name = "CreatedDate",updatable = false)
	private Date createdDate;
	
	@Column(name = "UpDatedBy")
	private String upDatedBy;
	
	@UpdateTimestamp
	@Column(name = "UpDatedDate")
	private Date upDatedDate;
	
	

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getMtoTId() {
		return mtoTId;
	}

	public void setMtoTId(Long mtoTId) {
		this.mtoTId = mtoTId;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
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
	
}
