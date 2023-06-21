package com.travel.travtronics.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftSlotsResponse {

	private Long headerId;

	private Long lineId;

	private String slotName;
	
	private Long orgId;

	private String startTime;

	private String endTime;

	private Double duration;

	private Boolean isSlotBooked;

}
