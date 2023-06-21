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

import com.travel.travtronics.util.EnumStatus;
import com.travel.travtronics.util.EnumYesNo;

import lombok.Data;

@Data
@Entity
@Table(name = "sla_lines")
public class SlaLinesModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "header_id")
	private Long headerId;
		
	@Column(name = "from_status_id")
	private Integer fromStatusId;
	
	@Column(name = "to_status_id")
	private Integer toStatusId;
	
	@Column(name = "sla_name")
	private String slaName;
	
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
	@Column(name = "customer_visible")
	private EnumYesNo customerVisible;
	
	@Column(name = "order_no")
	private Integer orderNo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "record_status")
	private EnumStatus recordStatus;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;
}
