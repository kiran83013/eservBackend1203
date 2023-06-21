package com.travel.travtronics.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShiftSlotsListResponse {

	private Long userId;
	
	private Long shiftId;
	
	private List<ShiftSlotsResponse> slotsList;
	
	
}
