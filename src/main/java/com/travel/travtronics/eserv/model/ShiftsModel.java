package com.travel.travtronics.eserv.model;

import java.time.LocalDate;
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


import lombok.Getter;
import lombok.Setter;

import com.travel.travtronics.util.EnumYesNo;
import com.travel.travtronics.util.EnumStatus;

@Getter
@Setter
@Entity
@Table(name = "shifts_manager")
public class ShiftsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "shift_id")
	private Long id;

	@Column(name = "shift_name")
	private String shiftName;
	
	@Column(name = "shift_code")
	private String shiftCode;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "flexi_break")
	private EnumYesNo flexiBreak;
	
	@Column(name = "flexi_duration")
	private double flexiDuration;
	
	@Column(name = "break_template_id")
	private Integer breakTemplateId;
	
	@Column(name = "org_ids")
	private Long orgId;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "shift_duration")
	private String  shiftDuration;
	
	@Column(name = "start_of_the_week")
	private String startOfTheWeek;
	
	@Column(name = "holiday_cal_id")
	private Integer holidayCalId;
	
	@Column(name = "timie_zone")
	private String timieZoneCode;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "shift_status")
	private EnumStatus shiftStatus;
	
	@Column(name = "created_by", updatable = false)
	private Integer createdBy;

	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;
	
	
	
	
}
