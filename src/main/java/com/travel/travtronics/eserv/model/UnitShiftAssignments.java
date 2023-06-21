package com.travel.travtronics.eserv.model;

import java.time.LocalDate;
import java.util.Date;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.response.UnitSearchResponse;
import com.travel.travtronics.response.UnitSearchResponseDto;
import com.travel.travtronics.util.EnumStatus;

@Entity
@Table(name = "unit_shift_assignments")

@SqlResultSetMappings({		
	@SqlResultSetMapping(name = "unit_search", classes = @ConstructorResult(targetClass = UnitSearchResponseDto.class, columns = {
			@ColumnResult(name = "costCenter", type = String.class),
			@ColumnResult(name = "costCenterName", type = String.class),
			@ColumnResult(name = "businessUnitId", type = Long.class),
			@ColumnResult(name = "businessUnitName", type = String.class),
			@ColumnResult(name = "project", type = Long.class),
			@ColumnResult(name = "projectName", type = String.class),
			@ColumnResult(name = "category", type = Long.class),
			@ColumnResult(name = "categoryName", type = String.class),
			@ColumnResult(name = "type", type = Long.class),
			@ColumnResult(name = "typeName", type = String.class),
			@ColumnResult(name = "buildingInformationId", type = Long.class),
			@ColumnResult(name = "buildingName", type = String.class),
			@ColumnResult(name = "floorId", type = Long.class),
			@ColumnResult(name = "floorName", type = String.class),
			@ColumnResult(name = "shiftFrom", type = String.class),
			@ColumnResult(name = "shiftTo", type = String.class),
			@ColumnResult(name = "shiftId", type = Long.class),
			@ColumnResult(name = "shiftName", type = String.class),
			@ColumnResult(name = "unitId", type = Long.class),
			@ColumnResult(name = "unitName", type = String.class),
			@ColumnResult(name = "location", type = String.class),
			@ColumnResult(name = "locationName", type = String.class),
			@ColumnResult(name = "timeZoneId", type = String.class),
			@ColumnResult(name = "timeZoneName", type = String.class),
			@ColumnResult(name = "unitShiftAssignmentId", type = Long.class),@ColumnResult(name = "orgId", type = Long.class),@ColumnResult(name = "imageUploads", type = String.class)})),
	@SqlResultSetMapping(name = "unit_shift_slot_search", classes = @ConstructorResult(columns = {
			@ColumnResult(name = "costCenter", type = Long.class),
			@ColumnResult(name = "costCenterName", type = String.class),
			@ColumnResult(name = "businessUnit", type = Long.class),
			@ColumnResult(name = "businessUnitName", type = String.class),
			@ColumnResult(name = "project", type = Long.class),
			@ColumnResult(name = "projectName", type = String.class),
			@ColumnResult(name = "category", type = Long.class),
			@ColumnResult(name = "categoryName", type = String.class),
			@ColumnResult(name = "type", type = Long.class),
			@ColumnResult(name = "typeName", type = String.class),
			@ColumnResult(name = "buildingInformationId", type = Long.class),
			@ColumnResult(name = "buildingName", type = String.class),
			@ColumnResult(name = "floorInformationId", type = Long.class),
			@ColumnResult(name = "floorName", type = String.class),
			@ColumnResult(name = "shiftFrom", type = String.class),
			@ColumnResult(name = "shiftTo", type = String.class),
			@ColumnResult(name = "shiftId", type = Long.class),
			@ColumnResult(name = "shiftName", type = String.class),
			@ColumnResult(name = "unitId", type = Long.class),
			@ColumnResult(name = "unitName", type = String.class),
			@ColumnResult(name = "location", type = String.class),
			@ColumnResult(name = "locationName", type = String.class),@ColumnResult(name = "slotTemplateId", type = Long.class),
			@ColumnResult(name = "slotTemplateName", type = String.class),
			@ColumnResult(name = "timeZone", type = String.class),@ColumnResult(name = "timeZoneName", type = String.class),
			@ColumnResult(name = "unitShiftAssignmentId", type = Long.class),@ColumnResult(name = "unitShiftSlotAssignmentId", type = Long.class),@ColumnResult(name = "orgId", type = Long.class)}, targetClass = UnitSearchResponse.class)) })
public class UnitShiftAssignments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "unit_id")
	private Long unitId;

	@Column(name = "shift_id")
	private Long shiftId;
	

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "org_id")
	private Long orgId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private EnumStatus status;

	@CreationTimestamp
	@Column(name = "Created_Date", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "Updated_Date")
	private Date updatedDate;

	@Column(name = "Created_By", updatable = false)
	private Long createdBy;

	@Column(name = "Updated_By")
	private Long updatedBy;

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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	
}
