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

import com.travel.travtronics.enums.Action;
import com.travel.travtronics.enums.StatusActive;

import lombok.Data;

@Data
@Entity
@Table(name ="bpf_config_header")
public class BpfConfigHeader {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID" )
	private Long ID;
	
	@Column(name ="OrganizationId" )
	private Long organizationId;
	
	
	@Column(name = "ConfigName")
	private String configName;
	
	@Column(name = "Module")
	private Long module;
	
	@Column(name = "SubModule")
	private Long subModule;
	
	@Column(name = "TransitionID")
	private Long transitionID;
	
	@Column(name = "StatusFrom")
	private Long statusFrom;
	
	@Column(name = "StatusTo")
	private Long statusTo;
	
	@Column(name = "ActionType")
	@Enumerated(EnumType.STRING)
	private Action actionType;
	
	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private StatusActive status;
	
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
	
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public Long getModule() {
		return module;
	}

	public void setModule(Long module) {
		this.module = module;
	}

	public Long getSubModule() {
		return subModule;
	}

	public void setSubModule(Long subModule) {
		this.subModule = subModule;
	}

	public Long getTransitionID() {
		return transitionID;
	}

	public void setTransitionID(Long transitionID) {
		this.transitionID = transitionID;
	}

	public Long getStatusFrom() {
		return statusFrom;
	}

	public void setStatusFrom(Long statusFrom) {
		this.statusFrom = statusFrom;
	}

	public Long getStatusTo() {
		return statusTo;
	}

	public void setStatusTo(Long statusTo) {
		this.statusTo = statusTo;
	}

	public Action getActionType() {
		return actionType;
	}

	public void setActionType(Action actionType) {
		this.actionType = actionType;
	}

	public StatusActive getStatus() {
		return status;
	}

	public void setStatus(StatusActive status) {
		this.status = status;
	}


	
}
