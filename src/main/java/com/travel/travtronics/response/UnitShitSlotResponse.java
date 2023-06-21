package com.travel.travtronics.response;

import java.time.LocalDateTime;

import com.travel.travtronics.request.UnitSlotShiftMappingRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitShitSlotResponse extends UnitSlotShiftMappingRequest{

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

}
