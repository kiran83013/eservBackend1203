package com.travel.travtronics.response;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.travel.travtronics.enums.Action;
import com.travel.travtronics.enums.StatusActive;
import com.travel.travtronics.eserv.model.BpfConfigHeader;
import com.travel.travtronics.eserv.model.BpfConfigLine;

public class BpfConfigRequest {

	private BpfConfigHeaderRequest configHeader;
	private List<BpfConfigLineRequest> configLines;

	public BpfConfigHeaderRequest getConfigHeader() {
		return configHeader;
	}

	public void setConfigHeader(BpfConfigHeaderRequest configHeader) {
		this.configHeader = configHeader;
	}

	public List<BpfConfigLineRequest> getConfigLines() {
		return configLines;
	}

	public void setConfigLines(List<BpfConfigLineRequest> configLines) {
		this.configLines = configLines;
	}

	public static class BpfConfigHeaderRequest {
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

		private Long createdBy;

		private Long updatedBy;

		private Date createdDate;

		private Date updatedDate;

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

	}

	public static class BpfConfigLineRequest {
		private Long lineID;

		private Long headerID;

		private Long letterTemplate;

		private String event;

		private Set<String> email;

		// private Long organizationId;

		private String notification;

		private String serviceCallURL;

		private String serviceCallHeaderDetails;

		private String serviceCallResponse;

		private String remarks;

		private StatusActive status;

		// private Long createdBy;

		// private Date createdDate;

		// private Long updatedBy;

		// private Date updatedDate;

		private Set<String> emailcc;

		private Set<String> emailbcc;

		private String emailTemplate;

		private Long deleteFlag;

		private Long deletedBy;

		public Long getLineID() {
			return lineID;
		}

		public void setLineID(Long lineID) {
			this.lineID = lineID;
		}

		public Long getHeaderID() {
			return headerID;
		}

		public void setHeaderID(Long headerID) {
			this.headerID = headerID;
		}

		public Long getLetterTemplate() {
			return letterTemplate;
		}

		public void setLetterTemplate(Long letterTemplate) {
			this.letterTemplate = letterTemplate;
		}

		public String getEvent() {
			return event;
		}

		public void setEvent(String event) {
			this.event = event;
		}

		public Set<String> getEmail() {
			return email;
		}

		public void setEmail(Set<String> email) {
			this.email = email;
		}

		public String getNotification() {
			return notification;
		}

		public void setNotification(String notification) {
			this.notification = notification;
		}

		public String getServiceCallURL() {
			return serviceCallURL;
		}

		public void setServiceCallURL(String serviceCallURL) {
			this.serviceCallURL = serviceCallURL;
		}

		public String getServiceCallHeaderDetails() {
			return serviceCallHeaderDetails;
		}

		public void setServiceCallHeaderDetails(String serviceCallHeaderDetails) {
			this.serviceCallHeaderDetails = serviceCallHeaderDetails;
		}

		public String getServiceCallResponse() {
			return serviceCallResponse;
		}

		public void setServiceCallResponse(String serviceCallResponse) {
			this.serviceCallResponse = serviceCallResponse;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public StatusActive getStatus() {
			return status;
		}

		public void setStatus(StatusActive status) {
			this.status = status;
		}

		public Set<String> getEmailcc() {
			return emailcc;
		}

		public void setEmailcc(Set<String> emailcc) {
			this.emailcc = emailcc;
		}

		public Set<String> getEmailbcc() {
			return emailbcc;
		}

		public void setEmailbcc(Set<String> emailbcc) {
			this.emailbcc = emailbcc;
		}

		public String getEmailTemplate() {
			return emailTemplate;
		}

		public void setEmailTemplate(String emailTemplate) {
			this.emailTemplate = emailTemplate;
		}

		public Long getDeleteFlag() {
			return deleteFlag;
		}

		public void setDeleteFlag(Long deleteFlag) {
			this.deleteFlag = deleteFlag;
		}

		public Long getDeletedBy() {
			return deletedBy;
		}

		public void setDeletedBy(Long deletedBy) {
			this.deletedBy = deletedBy;
		}

	}

	public BpfConfigHeader convertHeaderDtoToJson(Boolean onCreate) {

		BpfConfigHeader headerModel = new BpfConfigHeader();

		if (this.getConfigHeader().getID() != null && this.getConfigHeader().getID() != 0)
			headerModel.setID(this.getConfigHeader().getID());
		headerModel.setActionType(this.getConfigHeader().getActionType());
		headerModel.setConfigName(this.getConfigHeader().getConfigName());
		headerModel.setModule(this.getConfigHeader().getModule());
		headerModel.setOrganizationId(this.getConfigHeader().getOrganizationId());
		headerModel.setStatus(this.getConfigHeader().getStatus());
		headerModel.setSubModule(this.getConfigHeader().getSubModule());
		headerModel.setStatusFrom(this.getConfigHeader().getStatusFrom());
		headerModel.setStatusTo(this.getConfigHeader().getStatusTo());
		headerModel.setTransitionID(this.getConfigHeader().getTransitionID());
		if (onCreate)
			headerModel.setCreatedBy(this.getConfigHeader().getCreatedBy());
		else
			headerModel.setUpdatedBy(this.getConfigHeader().getUpdatedBy());

		return headerModel;

	}

	public List<BpfConfigLine> convertLineDtoToJson(BpfConfigHeader headerModel, Boolean onCreate) {

		return this.getConfigLines().stream().map(json -> {

			BpfConfigLine line = new BpfConfigLine();

			if (json.getLineID() != null && json.getLineID() != 0)
				line.setLineID(json.getLineID());

			line.setHeaderID(headerModel.getID());
			line.setOrganizationId(headerModel.getOrganizationId());
			if (onCreate)
				line.setCreatedBy(headerModel.getCreatedBy());
			else
				line.setUpdatedBy(headerModel.getUpdatedBy());

			if (json.getDeletedBy() != null)
				line.setDeletedBy(json.getDeletedBy());
			if (json.getDeleteFlag() != null)
				line.setDeleteFlag(json.getDeleteFlag());
			line.setLetterTemplate(json.getLetterTemplate());
			line.setEvent(json.getEvent());
			line.setNotification(json.getNotification());
			line.setServiceCallHeaderDetails(json.getServiceCallHeaderDetails());
			line.setServiceCallResponse(json.getServiceCallResponse());
			line.setServiceCallURL(json.getServiceCallURL());
			line.setRemarks(json.getRemarks());
			line.setStatus(json.getStatus());
			line.setEmailTemplate(json.getEmailTemplate());

			if (json.getEmail() != null && !json.getEmail().isEmpty())
				line.setEmail(json.getEmail().stream().collect(Collectors.joining(",")));
			if (json.getEmailbcc() != null && !json.getEmailbcc().isEmpty())
				line.setEmailbcc(json.getEmailbcc().stream().collect(Collectors.joining(",")));
			if (json.getEmailcc() != null && !json.getEmailcc().isEmpty())
				line.setEmailcc(json.getEmailcc().stream().collect(Collectors.joining(",")));
			return line;

		}).collect(Collectors.toList());
	}

	public BpfConfigRequest convertModelToJson(BpfConfigHeader headerModel, List<BpfConfigLine> lines) {

		BpfConfigHeaderRequest configHeader = new BpfConfigHeaderRequest();
		configHeader.setID(headerModel.getID());
		configHeader.setActionType(headerModel.getActionType());
		configHeader.setConfigName(headerModel.getConfigName());
		configHeader.setModule(headerModel.getModule());
		configHeader.setOrganizationId(headerModel.getOrganizationId());
		configHeader.setStatus(headerModel.getStatus());
		configHeader.setSubModule(headerModel.getSubModule());
		configHeader.setStatusFrom(headerModel.getStatusFrom());
		configHeader.setStatusTo(headerModel.getStatusTo());
		configHeader.setTransitionID(headerModel.getTransitionID());
		configHeader.setCreatedBy(headerModel.getCreatedBy());
		configHeader.setUpdatedBy(headerModel.getUpdatedBy());
		configHeader.setCreatedDate(headerModel.getCreatedDate());
		configHeader.setUpdatedDate(headerModel.getUpdatedDate());
		this.setConfigHeader(configHeader);
		this.setConfigLines(lines.stream().map(json -> {
			BpfConfigLineRequest line = new BpfConfigLineRequest();

			line.setLineID(json.getLineID());

			line.setHeaderID(json.getHeaderID());

			line.setDeletedBy(json.getDeletedBy());
			line.setDeleteFlag(json.getDeleteFlag());
			line.setLetterTemplate(json.getLetterTemplate());
			line.setEvent(json.getEvent());
			line.setNotification(json.getNotification());
			line.setServiceCallHeaderDetails(json.getServiceCallHeaderDetails());
			line.setServiceCallResponse(json.getServiceCallResponse());
			line.setServiceCallURL(json.getServiceCallURL());
			line.setRemarks(json.getRemarks());
			line.setStatus(json.getStatus());
			line.setEmailTemplate(json.getEmailTemplate());

			if (json.getEmail() != null && !json.getEmail().isEmpty()) {
				line.setEmail(new HashSet<>(Arrays.asList(json.getEmail().split(","))));
			}

			if (json.getEmailbcc() != null && !json.getEmailbcc().isEmpty()) {
				line.setEmailbcc(new HashSet<>(Arrays.asList(json.getEmailbcc().split(","))));
			}

			if (json.getEmailcc() != null && !json.getEmailcc().isEmpty()) {
				line.setEmailcc(new HashSet<>(Arrays.asList(json.getEmailcc().split(","))));
			}

			return line;
		}).collect(Collectors.toList()));
		return this;

	}
}
