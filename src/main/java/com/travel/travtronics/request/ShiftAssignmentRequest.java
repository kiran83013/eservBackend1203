package com.travel.travtronics.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.travel.travtronics.util.EnumStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftAssignmentRequest {

	private Long id;
	@NotNull
	private Long userId;
	@NotNull
	private Long shiftId;
	
	private LocalDate startDate;

	private LocalDate endDate;

	private Integer createdBy;

	private Integer updatedBy;

	private EnumStatus status;
	@NotNull
	private Long orgId;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

}
