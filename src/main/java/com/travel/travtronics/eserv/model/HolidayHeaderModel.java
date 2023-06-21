package com.travel.travtronics.eserv.model;

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
@Table(name = "holiday_template_header")
@Getter
@Setter
public class HolidayHeaderModel extends BaseModel {

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

	@Column(name = "year")
	private String year;

	@Column(name = "category")
	private String category;

	@Column(name = "type")
	private String type;

	@Column(name = "time_zone")
	private String timeZone;

	@Column(name = "country")
	private String country;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EnumStatus status;

}
