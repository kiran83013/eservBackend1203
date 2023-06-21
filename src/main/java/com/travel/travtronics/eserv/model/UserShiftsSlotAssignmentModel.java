package com.travel.travtronics.eserv.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
import com.travel.travtronics.util.EnumStatus;

@Getter
@Setter
@Entity
@Table(name = "user_shift_slot_assignments")
public class UserShiftsSlotAssignmentModel extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "org_id")
	private Long orgId;

	@Column(name = "shift_id")
	private Long shiftId;

	@Column(name = "slot_template_id")
	private Long slotTemplateId;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private EnumStatus status;

}
