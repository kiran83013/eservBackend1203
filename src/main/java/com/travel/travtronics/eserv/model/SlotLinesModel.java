package com.travel.travtronics.eserv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "slots_template_lines")
@Getter
@Setter
public class SlotLinesModel extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "header_id")
	private Long headerId;

	@Column(name = "name")
	private String name;
	
	@Column(name = "org_id")
	private Long orgId;
	
	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "duration")
	private Double duration;

	@Column(name = "is_deleted")
	private Boolean isDeleted;
}
