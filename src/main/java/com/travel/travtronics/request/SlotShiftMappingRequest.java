package com.travel.travtronics.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.travel.travtronics.util.EnumStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlotShiftMappingRequest {

	private Long id;
	@NotNull
	private Long userId;
	@NotNull
	private Long shiftId;
	@NotNull
	private Long slotTemplateId;
	@NotNull
	private Long orgId;
	
	private LocalDate startDate;

	private LocalDate endDate;

	private EnumStatus status;

	private Integer createdBy;

	private Integer updatedBy;

}
