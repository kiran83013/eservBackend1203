package com.travel.travtronics.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.travel.travtronics.util.EnumStatus;

public class UnitSlotShiftMappingRequest {
	
	private Long id;
	@NotNull
	private Long unitId;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	
}
