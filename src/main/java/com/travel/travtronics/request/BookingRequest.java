package com.travel.travtronics.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.travel.travtronics.util.EnumStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
	private Long bookingId;

	@NotNull
	private Long slotId;
	@NotNull
	private Long slotLineId;
	@NotNull
	private Long userId;
	@NotNull
	private Long shiftId;

	private Long orgId;
	
	private LocalDate bookingDate;

	private EnumStatus status;

	private Integer createdBy;

	private Integer updatedBy;
}
