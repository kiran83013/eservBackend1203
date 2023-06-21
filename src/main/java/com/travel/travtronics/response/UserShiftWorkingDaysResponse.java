package com.travel.travtronics.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShiftWorkingDaysResponse {

	private Long userId;
	
	private Long shiftId;
	
	private Integer monthNo;
	
	private Integer yearNo;
	
	private String dayDate;
	
	private Integer weekOff;
	
	private Integer isItHoliday;
	
	
}
