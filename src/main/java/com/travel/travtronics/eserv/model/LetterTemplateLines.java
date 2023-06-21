package com.travel.travtronics.eserv.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.converter.StringConverter;
import com.travel.travtronics.util.EnumStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "bpf_letter_template_lines")
public class LetterTemplateLines {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "letter_Line_Id", nullable = false)
	private Long letterLineId;

	@Column(name = "letter_Id")
	private Long letterId;

	@Column(name = "organization_Id")
	private Long organizationId;

	@Column(name = "language_Id")
	private Long languageId;

	@Column(name = "html_Editor", columnDefinition = "LONGTEXT")
	private String htmlEditor;

	@Column(name = "created_By", updatable = false)
	private Long createdBy;

	@CreationTimestamp
	@Column(name = "created_Date", updatable = false)
	private Date createdDate;

	@Column(name = "updated_By")
	private Long updatedBy;

	@UpdateTimestamp
	@Column(name = "updated_Date")
	private Date updatedDate;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EnumStatus status;

	@Convert(converter = StringConverter.class)
	@Column(name = "line_images")
	private List<String> images;

}
