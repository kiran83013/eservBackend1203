package com.travel.travtronics.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.travel.travtronics.util.EnumStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlaRequestData {

	private Integer orgId;
	
	private Integer businessUnit;
	
	private Integer costCenter;
	
	private Integer location;
	
	private Integer business;
	
	@Min(value = 1, message = "SerivceType must be greater than 0")
	@NotNull(message = "SerivceType Not Null")
	private Long serivceType;
	
	@Min(value = 1, message = "ShiftId must be greater than 0")
	@NotNull(message = "ShiftId Not Null")
	private Long shiftId;
	
	@Min(value = 1, message = "SlaTime must be greater than 0")
	@NotNull(message = "SlaTime Not Null")
	private Long slaTime;
	
	private String note;
	
	@NotNull(message = "startDate is should not be empty")
	private Date startDate;
	
	private Date endDate;
	
	@Min(value = 1, message = "createdBy must be greater than 0")
	@NotNull(message = "createdBy Not Null")
	private Long createdBy;
	
	private EnumStatus recordStatus;
	
	private List<SlaLinesRequestData> slaLines;
}
