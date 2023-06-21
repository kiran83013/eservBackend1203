package com.travel.travtronics.eserv.model;

import java.time.LocalDateTime;
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

import com.travel.travtronics.util.EnumStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "sla_header")
public class SlaHeaderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "business_unit")
	private Integer businessUnit;
	
	@Column(name = "cost_center")
	private Integer costCenter;
	
	@Column(name = "location")
	private Integer location;
	
	@Column(name = "business")
	private Integer business;
	
	@Column(name = "serivce_type")
	private Long serivceType;
	
	@Column(name = "shift_id")
	private Long shiftId;
	
	@Column(name = "sla_time")
	private Long slaTime;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "record_status")
	private EnumStatus recordStatus;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;
}
