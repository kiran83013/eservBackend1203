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
@Table(name ="bpf_mail_template")
public class BpfMailTemplate extends WhoColumnsModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "OrganizationId")
	private Long organizationId;
	
	
	@Column(name = "TemplateName")
	private String templateName;
	
	@Column(name = "ViewName")
	private String viewName;
	
	@Column(name = "Subject")
	private String subject;
	
	@Column(name = "Body",columnDefinition = "LONGTEXT")
	private String body;
	
	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private StatusActive status;
	
	@Column(name = "StartDate")
	private Date startDate;
	
	@Column(name = "EndDate")
	private Date endDate;


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

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public StatusActive getStatus() {
		return status;
	}

	public void setStatus(StatusActive status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	
}
