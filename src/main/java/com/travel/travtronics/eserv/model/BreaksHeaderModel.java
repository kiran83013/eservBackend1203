package com.travel.travtronics.eserv.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
import com.travel.travtronics.util.EnumStatus;

@Entity
@Table(name = "breaks_template_header")
@Getter
@Setter
public class BreaksHeaderModel extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;
	
	@Column(name = "org_id")
	private Long orgId;

	@Column(name = "duration_of_breaks")
	private String durationOfBreaks;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EnumStatus status;

}
