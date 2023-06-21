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

@Entity
@Table(name= "team_leader_to_team")
public class TeamLeadertoTeam extends WhoColumnsModel{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="TId")
	private Long tId;
		
	@Column(name = "EndDate")
	private Date endDate;
	
	@Column(name = "Module")
	private String module;
	
	@Column(name = "Organization")
	private String organization;
	
	@Column(name = "StartDate")
	private Date startDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private StatusActive status;
	
	@Column(name = "Team")
	private String team;
	
	@Column(name = "TeamLeader")
	private String teamLeader;
	

	public Long gettId() {
		return tId;
	}

	public void settId(Long tId) {
		this.tId = tId;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
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

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}


}
