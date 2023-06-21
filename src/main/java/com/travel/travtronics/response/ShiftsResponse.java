package com.travel.travtronics.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.travel.travtronics.request.ShiftWorkingDaysRequest;
import com.travel.travtronics.util.EnumYesNo;
import com.travel.travtronics.util.EnumStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftsResponse {

	private Long id;

	private String shiftName;
	
	private String shiftCode;
	
	private EnumYesNo flexiBreak;
	
	private double flexiDuration;
	
	private Integer breakTemplateId;
	
	private Long orgId;
	
	private String startTime;
	
	private String endTime;
	
	private String  shiftDuration;
	
	private String startOfTheWeek;
	
	private Integer holidayCalId;
	
	private String timieZoneCode;
	
	private String notes;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private EnumStatus shiftStatus;
	
	private Integer createdBy;

	private LocalDateTime createdDate;

	private Integer updatedBy;

	private LocalDateTime updatedDate;
		
	private List<Integer> weekOffsList;
	
	private List<Integer> excludedMonthsList;
	
	private List<ShiftWorkingDaysRequest> shiftWorkingDays;
	
	private Integer loggedInUserId;

}
