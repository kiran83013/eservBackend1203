package com.travel.travtronics.eserv.model; 

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import com.travel.travtronics.response.UserSearchResponse;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_appointment_booking")
@Getter
@Setter

@SqlResultSetMappings({
	@SqlResultSetMapping(name = "user_shift_slot_search", classes = @ConstructorResult(columns = {
			@ColumnResult(name = "userId", type = Long.class),
			@ColumnResult(name = "costCenter", type = String.class),
			@ColumnResult(name = "costCenterName", type = String.class),
			@ColumnResult(name = "userCreatedDate", type = String.class),
			@ColumnResult(name = "department", type = String.class),
			@ColumnResult(name = "departmentName", type = String.class),
			@ColumnResult(name = "email", type = String.class),
			@ColumnResult(name = "phoneNumber", type = String.class),
			@ColumnResult(name = "firstName", type = String.class),
			@ColumnResult(name = "lastName", type = String.class),
			@ColumnResult(name = "userName", type = String.class),
			@ColumnResult(name = "location", type = String.class),
			@ColumnResult(name = "locationName", type = String.class),
			@ColumnResult(name = "supervisor", type = String.class),
			@ColumnResult(name = "supervisorName", type = String.class),
			@ColumnResult(name = "shiftFrom", type = String.class),
			@ColumnResult(name = "shiftTo", type = String.class),
			@ColumnResult(name = "shiftId", type = Long.class),
			@ColumnResult(name = "shiftName", type = String.class),
			@ColumnResult(name = "timeZone", type = String.class),
			@ColumnResult(name = "timeZoneName", type = String.class),
			@ColumnResult(name = "slotTemplateId", type = Long.class),
			@ColumnResult(name = "slotTemplateName", type = String.class),
			@ColumnResult(name = "userShiftAssignmentId", type = Long.class),
			@ColumnResult(name = "userShiftSlotAssignmentId", type = Long.class) }, targetClass = UserSearchResponse.class)) })
public class AppointMentBookingModel extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booking_id")
	private Long bookingId;

	@Column(name = "slot_id")
	private Long slotId;

	@Column(name = "slot_line_id")
	private Long slotLineId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "shift_id")
	private Long shiftId;

	@Column(name = "org_id")
	private Long orgId;

	@Column(name = "booking_date")
	private LocalDate bookingDate;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private  com.travel.travtronics.util.EnumStatus status;

}
