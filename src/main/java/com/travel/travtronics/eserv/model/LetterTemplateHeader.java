package com.travel.travtronics.eserv.model;

import java.time.LocalDateTime;

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

import com.travel.travtronics.util.EnumStatus;

@Entity
@Table(name = "bpf_letter_template_header")
public class LetterTemplateHeader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "letter_Id")
	private Long letterId;
	
	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "letter_Name")
	private String letterName;
	
	@Column(name = "organization_Id")
	private Long organizationId;
	
	@Column(name = "created_By", updatable = false)
	private Long createdBy;
	
	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;
	
	@Column(name = "updated_by")
	private Long updatedBy;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EnumStatus status;

	public Long getLetterId() {
		return letterId;
	}

	public void setLetterId(Long letterId) {
		this.letterId = letterId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLetterName() {
		return letterName;
	}

	public void setLetterName(String letterName) {
		this.letterName = letterName;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}
}
