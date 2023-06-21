package com.travel.travtronics.eserv.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "holiday_template_lines")
@Getter
@Setter
public class HolidayLinesModel extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "header_id")
	private Long headerId;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;

	@Column(name = "org_id")
	private Long orgId;

	@Column(name = "category")
	private String category;

	@Column(name = "type")
	private String type;

	@Column(name = "madatory")
	private Boolean mandatory;

	@Column(name = "ot_applicable")
	private Boolean otApplicable;

	@Column(name = "holiday_date")
	private LocalDate holidayDate;

	@Column(name = "file_uri")
	private String fileUri;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

}
