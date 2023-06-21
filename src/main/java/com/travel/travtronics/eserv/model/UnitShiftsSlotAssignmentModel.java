package com.travel.travtronics.eserv.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.util.EnumStatus;

@Entity
@Table(name = "unit_shift_slot_assignments")
public class UnitShiftsSlotAssignmentModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "unit_id")
	private Long unitId;
	
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
	
	@Column(name = "created_by", updatable = false)
	private Integer createdBy;

	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	public Long getSlotTemplateId() {
		return slotTemplateId;
	}

	public void setSlotTemplateId(Long slotTemplateId) {
		this.slotTemplateId = slotTemplateId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}
