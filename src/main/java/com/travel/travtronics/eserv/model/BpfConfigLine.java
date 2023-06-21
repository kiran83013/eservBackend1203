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
@Table(name ="bpf_config_line")
public class BpfConfigLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="LINEID" )
	private Long lineID;
	
	@Column(name = "HEADERID")
	private Long headerID;
	
	@Column(name = "LetterTemplate ")
	private Long letterTemplate ;
	
	@Column(name = "Event")
	private String event;
	
//	@Email
	@Column(name = "Email")
	private String email;
	
	@Column(name ="OrganizationId" )
	private Long organizationId;
	
	@Column(name = "Notification")
	private String notification;
	
	@Column(name = "ServiceCallURL")
	private String serviceCallURL;
	
	@Column(name = "ServiceCallHeaderDetails")
	private String serviceCallHeaderDetails;
	
	@Column(name = "ServiceCallResponse")
	private String serviceCallResponse;
	
	@Column(name =  "Remarks",columnDefinition = "longtext")
	private String remarks;
	
	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private StatusActive status;
	
	@Column(name = "CreatedBy")
	private Long createdBy;
	
	@Column(name = "CreatedDate")
	private Date createdDate;
	
	@Column(name = "UpdatedBy")
	private Long updatedBy;
	
	@Column(name = "UpdatedDate")
	private Date updatedDate;
	
	@Column(name = "EmailCC")
	private String emailcc;
	
	@Column(name = "EmailBCC")
	private String emailbcc;
	
	@Column(name = "EmailTemplate")
	private String emailTemplate;
	
	@Column(name = "DeleteFlag")
	private Long deleteFlag;
	
	@Column(name = "DeletedBy")
	private Long deletedBy;
	
	

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getEmailcc() {
		return emailcc;
	}

	public void setEmailcc(String emailcc) {
		this.emailcc = emailcc;
	}

	public String getEmailbcc() {
		return emailbcc;
	}

	public void setEmailbcc(String emailbcc) {
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
