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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
@Entity
@Table(name = "person_language")
public class PersonLanguageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "RefId")
	private Long refId;

	@Column(name = "LanguageId")
	private Long languageId;

	@JsonProperty("languageRead")
	@Column(name = "LanguageRead")
	private Boolean read;

	@JsonProperty("languageWrite")
	@Column(name = "LanguageWrite")
	private Boolean write;

	@Column(name = "Speak")
	private Boolean speak;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;

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
	@Column(name = "OrganizationId")
	private Long organizationId;

}
