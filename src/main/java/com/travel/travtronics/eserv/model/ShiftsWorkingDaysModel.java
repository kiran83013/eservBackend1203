package com.travel.travtronics.eserv.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.travel.travtronics.util.EnumStatus;
import com.travel.travtronics.util.EnumYesNo;

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


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shifts_working_days")
public class ShiftsWorkingDaysModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "shift_id")
	private Long shiftId;
	
	@Column(name = "org_id")
	private Long orgId;
	
	@Column(name = "year_no")
	private Integer yearNo;
	
	@Column(name = "month_no")
	private Integer monthNo;
	
	@Column(name = "day_no")
	private Integer dayNo;
	
	@Column(name = "working_full_date")
	private LocalDate workingFullDate;
	
	@Column(name = "shift_start_time")
	private String shiftStartTime;
	
	@Column(name = "shift_end_time")
	private String shiftEndTime;
	
	@Column(name = "shift_duration")
	private Integer  shiftDuration;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "is_week_off")
	private EnumYesNo isWeekOff;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "is_holiday")
	private EnumYesNo isHoliday;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "is_exemption")
	private EnumYesNo isExemption;
	
	@Column(name = "record_status")
	@Enumerated(EnumType.STRING)
	private EnumStatus recordStatus;
	
	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

}
