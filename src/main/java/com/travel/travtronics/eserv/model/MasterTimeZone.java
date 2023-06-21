package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "master_timezone")
public class MasterTimeZone {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "ISO3166")
	private String iSO3166;

	@Column(name = "TimeZone")
	private String timeZone;

	@Column(name = "UTC")
	private String utc;

	@Column(name = "TimezoneDescription")
	private String timezoneDescription;

	@Column(name = "createdBY")
	private Integer createdBy;
	
	@Column(name="creationDate")
	private Date creationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getiSO3166() {
		return iSO3166;
	}

	public void setiSO3166(String iSO3166) {
		this.iSO3166 = iSO3166;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getUtc() {
		return utc;
	}

	public void setUtc(String utc) {
		this.utc = utc;
	}

	public String getTimezoneDescription() {
		return timezoneDescription;
	}

	public void setTimezoneDescription(String timezoneDescription) {
		this.timezoneDescription = timezoneDescription;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
