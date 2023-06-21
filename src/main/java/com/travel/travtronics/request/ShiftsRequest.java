package com.travel.travtronics.request;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.travel.travtronics.util.EnumYesNo;
import com.travel.travtronics.util.EnumStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShiftsRequest {

	private Long id;
	
	private Long orgId;
	
	private String shiftName;

	private String shiftCode;

	private EnumYesNo flexiBreak;

	private double flexiDuration;

	private Integer breakTemplateId;

	private String startTime;

	private String endTime;

	private String shiftDuration;

	private String startOfTheWeek;

	private Integer holidayCalId;

	private String timieZoneCode;

	private String notes;

	private LocalDate startDate;

	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	private EnumStatus shiftStatus;

	private List<Integer> weekOffsList;

	private List<Integer> excludedMonthsList;

	private List<ShiftWorkingDaysRequest> shiftWorkingDays;

	private Integer loggedInUserId;

}
