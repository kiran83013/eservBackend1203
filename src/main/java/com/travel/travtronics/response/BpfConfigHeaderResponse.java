package com.travel.travtronics.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travel.travtronics.enums.Action;
import com.travel.travtronics.enums.StatusActive;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BpfConfigHeaderResponse {

	private Long ID;

	private Long organizationId;

	private String configName;

	private Long module;

	private Long subModule;

	private Long transitionID;

	private Long statusFrom;

	private Long statusTo;

	private Action actionType;

	private StatusActive status;
	
	private Long letterTemplate ;

	private Long createdBy;

	private Long updatedBy;

	private Date createdDate;

	private Date updatedDate;

	private String moduleName;

	private String subModuleName;

	private String transitionName;
	
	private String statusFromName;

	private String statusToName;
	
	private String letterTemplateName ;

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
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

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public String getTransitionName() {
		return transitionName;
	}

	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}

	public String getStatusFromName() {
		return statusFromName;
	}

	public void setStatusFromName(String statusFromName) {
		this.statusFromName = statusFromName;
	}

	public String getStatusToName() {
		return statusToName;
	}

	public void setStatusToName(String statusToName) {
		this.statusToName = statusToName;
	}

	public Long getLetterTemplate() {
		return letterTemplate;
	}

	public void setLetterTemplate(Long letterTemplate) {
		this.letterTemplate = letterTemplate;
	}

	public String getLetterTemplateName() {
		return letterTemplateName;
	}

	public void setLetterTemplateName(String letterTemplateName) {
		this.letterTemplateName = letterTemplateName;
	}
	
	

}
