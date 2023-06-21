package com.travel.travtronics.response;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.travel.travtronics.request.SlaLinesRequestData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlaResponseData {

	private Long id;
	
	private Integer orgId;
	
	private Integer businessUnit;
	
	private Integer costCenter;
	
	private Integer location;
	
	private Integer business;
	
	private Long serivceType;
	
	private Long shiftId;
	
	private Long slaTime;
	
	private Long fromStatus;
	
	private Long toStatus;
	
	private String note;
	
	private Date startDate;
	
	private Date endDate;
	
	private Long createdBy;
	
	private LocalDateTime createdDate;
	
	private Long updatedBy;
	
	private LocalDateTime updatedDate;
	
	private List<SlaLinesRequestData> slaLines;
	
}
