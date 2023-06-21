package com.travel.travtronics.response;

import java.time.LocalDateTime;

import com.travel.travtronics.request.SlotShiftMappingRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShitSlotResponse extends SlotShiftMappingRequest {

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;
}
