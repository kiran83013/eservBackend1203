package com.travel.travtronics.request;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.travel.travtronics.util.EnumStatus;
import com.travel.travtronics.util.EnumYesNo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlaLinesRequestData {

	private Long id;

	private Integer orgId;
	
	@NotNull(message = "headerId is should not be empty")
	private Long headerId;
	
	private Integer fromStatusId;
	
	@Min(value = 1, message = "fromStatusId must be greater than 0")
	@NotNull(message = "fromStatusId Not Null")	
	private Integer toStatusId;
	
	@NotEmpty(message = "SerivceType Not Empty")
	@NotNull(message = "SerivceType Not Null")
	private String slaName;
	
	@Min(value = 1, message = "ShiftId must be greater than 0")
	@NotNull(message = "ShiftId Not Null")
	private Long shiftId;
	
	@Min(value = 1, message = "SlaTime must be greater than 0")
	@NotNull(message = "SlaTime Not Null")
	private Long slaTime;
	
	private String notes;
	
	@NotNull(message = "startDate is should not be empty")
	private Date startDate;
	
	private Date endDate;
	
	private EnumYesNo customerVisible;
	
	private Integer orderNo;
	
	private EnumStatus recordStatus;
	
	private Integer createdBy;
	
	private LocalDateTime createdDate;
	
	private Integer updatedBy;
	
	private LocalDateTime updatedDate;
	
		
}
