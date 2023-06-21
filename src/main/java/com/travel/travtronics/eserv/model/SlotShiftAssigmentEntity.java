package com.travel.travtronics.eserv.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.data.annotation.Immutable;

import com.travel.travtronics.util.EnumStatus;

import lombok.Getter;

@Entity
@Immutable
@Getter
public class SlotShiftAssigmentEntity {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "user_id", insertable = false, updatable = false)
	private Long userId;

	@Column(name = "user_name", insertable = false, updatable = false)
	private String userName;

	@Column(name = "shift_id", insertable = false, updatable = false)
	private Long shiftId;

	@Column(name = "shift_name", insertable = false, updatable = false)
	private String shiftName;

	@Column(name = "slot_template_name", insertable = false, updatable = false)
	private String slotTemplateName;

	@Column(name = "start_date", insertable = false, updatable = false)
	private LocalDate startDate;

	@Column(name = "end_date", insertable = false, updatable = false)
	private LocalDate endDate;

	@Column(name = "slot_template_id", insertable = false, updatable = false)
	private Long slotTemplateId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", insertable = false, updatable = false)
	private EnumStatus status;

	@Column(name = "created_by", insertable = false, updatable = false)
	private Integer createdBy;

	@Column(name = "created_date", insertable = false, updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "updated_by", insertable = false, updatable = false)
	private Integer updatedBy;

	@Column(name = "updated_date", insertable = false, updatable = false)
	private LocalDateTime updatedDate;

	@Column(name = "organization_name", insertable = false, updatable = false)
	private String organizationName;
	@Column(name = "org_id", insertable = false, updatable = false)
	private Long orgId;
}
