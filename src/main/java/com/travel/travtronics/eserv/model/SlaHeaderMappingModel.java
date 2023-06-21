package com.travel.travtronics.eserv.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.travel.travtronics.util.EnumStatus;
import com.travel.travtronics.util.QueryConst;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@Entity
@Immutable
@Subselect(value = QueryConst.GET_SLA_HEADER_LIST_QRY)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Value
public class SlaHeaderMappingModel {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "org_name")
	private String orgName;
	
	@Column(name = "business_unit")
	private Integer businessUnit;
	
	@Column(name = "business_unit_name")
	private String businessUnitName;
	
	@Column(name = "cost_center")
	private Integer costCenter;
	
	@Column(name = "cost_center_name")
	private String costCenterName;
	
	@Column(name = "location")
	private Integer location;
	
	@Column(name = "location_name")
	private String locationName;
	
	@Column(name = "business")
	private Integer business;
	
	@Column(name = "business_name")
	private String businessName;
	
	@Column(name = "serivce_type")
	private Long serivceType;
	
	@Column(name = "serivce_type_name")
	private String serivceTypeName;
	
	@Column(name = "shift_id")
	private Long shiftId;
	
	@Column(name = "shift_name")
	private String shiftName;
	
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
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;
}
