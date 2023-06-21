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


import lombok.Getter;
import lombok.Setter;
import com.travel.travtronics.util.EnumStatus;

@Getter
@Setter
@Entity
@Table(name = "shifts_exculed_months")
public class ShiftsExculedMonthsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "shift_id")
	private Long shiftId;
	
	@Column(name = "org_id")
	private Long orgId;

	@Column(name = "month_no")
	private Integer monthNo;
	
	@Column(name = "record_status")
	@Enumerated(EnumType.STRING)
	private EnumStatus recordStatus;
	
	@Column(name = "created_by", updatable = false)
	private Integer createdBy;

	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

}
