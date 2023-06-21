package com.travel.travtronics.eserv.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.travel.travtronics.request.SearchUnitDto;
import com.travel.travtronics.request.UnitSearchDto;
import com.travel.travtronics.response.UnitSearchResponse;
import com.travel.travtronics.response.UnitSearchResponseDto;

public class UnitCustomSearchRepositoryImpl implements UnitCustomSearchRepository{
	
	@PersistenceContext
	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<UnitSearchResponse> searchUnitShiftInfo(UnitSearchDto search) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ui.ID AS unitId, ui.Amenities AS amenities,ui.BusinessUnit AS businessUnit,ui.Category AS category,ui.Completion_Date AS completionDate,ui.Cost_Center AS costCenter,cc.Name AS costCenterName,ui.Created_By AS Created_By,ui.Description AS DESCRIPTION, \r\n" + 
				"ui.End_Date AS endDate,ui.Extent AS extent,ui.Extent_Uom AS extent_Uom,ui.FloorInformationId AS floorInformationId,ui.Height AS height,ui.Height_Uom AS height_Uom,ui.Image_Type AS image_Type,ui.Image_Uploads AS ImageUploads,\r\n" + 
				"ui.Location AS location,ui.Organization AS orgId,ui.Project AS project,ui.Start_Date AS startdate,ui.Status AS STATUS,ui.Tags AS tags,ui.Type AS TYPE, ui.Unit_Name AS unitName,ui.Unit_Number AS unitNumber,\r\n" + 
				"ui.Updated_By AS updatedBy,ui.Updated_Date AS updatedDate,ui.Propery_Type AS properyType,ui.Building_Information_Id AS buildingInformationId,\r\n" + 
				"bu.Name AS businessUnitName,p.Project_Name AS projectName,mc.Name AS categoryName,mct.name AS typeName,bi.Building_Name AS buildingName,\r\n" + 
				"fi.Floor_Name AS floorName,loc.Name AS locationName,ussa.slot_template_id AS slotTemplateId,sth.name AS slotTemplateName,\r\n" + 
				"shift.start_time AS shiftFrom,shift.end_time AS shiftTo,shift.shift_id AS shiftId,shift.shift_name AS shiftName,shift.timie_zone AS timeZone,tz.TimeZone AS timeZoneName,\r\n" + 
				"usa.id AS unitShiftAssignmentId,ussa.id AS unitShiftSlotAssignmentId FROM unit_information ui\r\n" + 
				"LEFT JOIN e_services.cost_center cc ON cc.CostcenterId=ui.cost_center\r\n" + 
				"LEFT JOIN e_services.business_unit bu ON bu.BusinessUnitId =ui.BusinessUnit\r\n" + 
				"LEFT JOIN e_services.project p ON p.ID = ui.project\r\n" + 
				"LEFT JOIN e_services.master_category mc ON mc.category_id = ui.category\r\n" + 
				"LEFT JOIN e_services.master_building_type mct ON mct.ID = ui.type\r\n" + 
				"LEFT JOIN e_services.building_information bi ON bi.ID = ui.Building_Information_Id\r\n" + 
				"LEFT JOIN e_services.floor_information fi ON fi.ID = ui.FloorInformationId\r\n" + 
				"LEFT JOIN e_services.location loc ON loc.LocationId=ui.location\r\n" + 
				"LEFT JOIN e_services.unit_shift_assignments usa ON usa.unit_id = ui.ID \r\n" + 
				"INNER JOIN e_services.shifts_manager shift ON shift.shift_id=usa.shift_id\r\n" + 
				"LEFT JOIN e_services.unit_shift_slot_assignments ussa ON ussa.unit_id = usa.unit_id AND ussa.shift_id = usa.shift_id\r\n" + 
				"LEFT JOIN e_services.slots_template_header sth ON sth.id=ussa.slot_template_id\r\n" + 
				"INNER JOIN e_services.master_timezone tz ON tz.ID=shift.timie_zone WHERE 1=1" +System.lineSeparator());
		if (search.getCostCenter() != null && search.getCostCenter() != 0) {
			sql.append("AND ui.Cost_Center = :costCenter" + System.lineSeparator());
			params.put("costCenter", search.getCostCenter());
		}
		if (search.getBusinessUnitId() != null && search.getBusinessUnitId() != 0) {
			sql.append("AND ui.BusinessUnit = :businessUnitId" + System.lineSeparator());
			params.put("businessUnitId", search.getBusinessUnitId());
		}
		if (search.getProject()!= null && search.getProject() != 0) {
			sql.append("AND ui.Project = :project" + System.lineSeparator());
			params.put("project", search.getProject());
		}
		if (search.getCategory() != null && search.getCategory() != 0) {
			sql.append("AND ui.Category = :category" + System.lineSeparator());
			params.put("category", search.getCategory());
		}
		if (search.getType()!= null && search.getType() != 0) {
			sql.append("AND ui.Type = :type" + System.lineSeparator());
			params.put("type", search.getType());
		}
		if (search.getBuildingInformationId() != null && search.getBuildingInformationId() != 0) {
			sql.append("AND ui.Building_Information_Id = :buildingInformationId" + System.lineSeparator());
			params.put("buildingInformationId", search.getBuildingInformationId());
		}
		if (search.getFloorId() != null && search.getFloorId() != 0) {
			sql.append("AND ui.FloorInformationId = :floorId" + System.lineSeparator());
			params.put("floorId", search.getFloorId());
		}
		if (search.getUnitId() != null && search.getUnitId() != 0) {
			sql.append("AND ui.ID= :unitId" + System.lineSeparator());
			params.put("unitId", search.getUnitId());
		}
		if (search.getLocationId() != null && !search.getLocationId().trim().isBlank()) {
			sql.append("AND ui.Location= :locationId" + System.lineSeparator());
			params.put("locationId", search.getLocationId());
		}
		if (search.getShiftFrom() != null && !search.getShiftFrom().trim().isBlank()) {
			sql.append("AND shift.start_time= :shiftFrom" + System.lineSeparator());
			params.put("shiftFrom", search.getShiftFrom());
		}
		if (search.getShiftTo() != null && !search.getShiftTo().isBlank()) {
			sql.append("AND shift.end_time= :shiftTo" + System.lineSeparator());
			params.put("shiftTo", search.getShiftTo());
		}
		if (search.getShiftId() != null && search.getShiftId() != 0) {
			sql.append("AND ussa.shift_id= :shiftId" + System.lineSeparator());
			params.put("shiftId", search.getShiftId());
		}
		if (search.getSlotTemplateId()!= null && search.getSlotTemplateId() != 0) {
			sql.append("AND ussa.slot_template_id = :slotTemplateId" + System.lineSeparator());
			params.put("slotTemplateId", search.getSlotTemplateId());
		}
		if (search.getTimeZone() != null && !search.getTimeZone().isBlank()) {
			sql.append("AND shift.timie_zone= :timeZone" + System.lineSeparator());
			params.put("timeZone", search.getTimeZone());
		}
	
		sql.append("ORDER BY ui.ID,shift.shift_id,ussa.slot_template_id");
		Query query = entityManager.createNativeQuery(sql.toString(),"unit_shift_slot_search");

		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());

		}
		
		
		@SuppressWarnings("unchecked")
		List<UnitSearchResponse> resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<UnitSearchResponseDto> searchUnitInfo(SearchUnitDto searchDto) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  ui.ID AS unitId, ui.Amenities AS amenities,ui.BusinessUnit AS businessUnitId,ui.Category AS category,ui.Completion_Date AS completionDate,ui.Cost_Center AS costCenter,cc.Name AS costCenterName,ui.Created_By AS Created_By,ui.Description AS DESCRIPTION, \r\n" + 
				"ui.End_Date AS endDate,ui.Extent AS extent,ui.Extent_Uom AS extent_Uom,ui.FloorInformationId AS floorId,ui.Height AS height,ui.Height_Uom AS height_Uom,ui.Image_Type AS image_Type,ui.Image_Uploads AS ImageUploads, \r\n" + 
				"ui.Location AS location,ui.Organization AS orgId,ui.Project AS project,ui.Start_Date AS startdate,ui.Status AS STATUS,ui.Tags AS tags,ui.Type AS TYPE, ui.Unit_Name AS unitName,ui.Unit_Number AS unitNumber,\r\n" + 
				"ui.Updated_By AS updatedBy,ui.Updated_Date AS updatedDate,ui.Propery_Type AS propertyType,ui.Building_Information_Id AS buildingInformationId,bu.Name AS businessUnitName,p.Project_Name AS projectName,mc.Name AS categoryName,mct.name AS typeName,bi.Building_Name AS buildingName,\r\n" + 
				"fi.Floor_Name AS floorName,loc.Name AS locationName,\r\n" + 
				"shift.start_time AS shiftFrom,shift.end_time AS shiftTo,shift.shift_id AS shiftId,shift.shift_name AS shiftName,shift.timie_zone AS timeZoneId,tz.TimeZone AS timeZoneName,usa.id AS unitShiftAssignmentId FROM unit_information ui\r\n" + 
				"LEFT JOIN e_services.cost_center cc ON cc.CostcenterId=ui.cost_center\r\n" + 
				"LEFT JOIN e_services.business_unit bu ON bu.BusinessUnitId =ui.BusinessUnit\r\n" + 
				"LEFT JOIN e_services.project p ON p.ID = ui.project\r\n" + 
				"LEFT JOIN e_services.master_category mc ON mc.category_id = ui.category\r\n" + 
				"LEFT JOIN e_services.master_building_type mct ON mct.ID = ui.type\r\n" + 
				"LEFT JOIN e_services.building_information bi ON bi.ID = ui.Building_Information_Id\r\n" + 
				"LEFT JOIN e_services.floor_information fi ON fi.ID = ui.FloorInformationId\r\n" + 
				"LEFT JOIN e_services.location loc ON loc.LocationId=ui.location\r\n" + 
				"LEFT JOIN e_services.unit_shift_assignments usa ON usa.unit_id = ui.ID \r\n" + 
				"LEFT JOIN e_services.shifts_manager shift ON shift.shift_id=usa.shift_id\r\n" + 
				"LEFT JOIN e_services.master_timezone tz ON tz.ID=shift.timie_zone WHERE 1=1"+ System.lineSeparator());
		if (searchDto.getCostCenter() != null && searchDto.getCostCenter() != 0) {
			sql.append("AND ui.Cost_Center = :costCenter" + System.lineSeparator());
			params.put("costCenter", searchDto.getCostCenter());
		}
		if (searchDto.getBusinessUnitId() != null && searchDto.getBusinessUnitId() != 0) {
			sql.append("AND ui.BusinessUnit = :businessUnitId" + System.lineSeparator());
			params.put("businessUnitId", searchDto.getBusinessUnitId());
		}
		if (searchDto.getProject()!= null && searchDto.getProject() != 0) {
			sql.append("AND ui.Project = :project" + System.lineSeparator());
			params.put("project", searchDto.getProject());
		}
		if (searchDto.getCategory() != null && searchDto.getCategory() != 0) {
			sql.append("AND ui.Category = :category" + System.lineSeparator());
			params.put("category", searchDto.getCategory());
		}
		if (searchDto.getType()!= null && searchDto.getType() != 0) {
			sql.append("AND ui.Type = :type" + System.lineSeparator());
			params.put("type", searchDto.getType());
		}
		if (searchDto.getBuildingInformationId() != null && searchDto.getBuildingInformationId() != 0) {
			sql.append("AND ui.Building_Information_Id = :buildingInformationId" + System.lineSeparator());
			params.put("buildingInformationId", searchDto.getBuildingInformationId());
		}
		if (searchDto.getFloorId() != null && searchDto.getFloorId() != 0) {
			sql.append("AND ui.FloorInformationId = :floorId" + System.lineSeparator());
			params.put("floorId", searchDto.getFloorId());
		}
		if (searchDto.getUnitId() != null && searchDto.getUnitId() != 0) {
			sql.append("AND ui.ID= :unitId" + System.lineSeparator());
			params.put("unitId", searchDto.getUnitId());
		}
		if (searchDto.getLocationId() != null && !searchDto.getLocationId().trim().isBlank()) {
			sql.append("AND ui.Location = :locationId" + System.lineSeparator());
			params.put("locationId", searchDto.getLocationId());
		}
		if (searchDto.getShiftFrom() != null && !searchDto.getShiftFrom().trim().isBlank()) {
			sql.append("AND shift.start_time = :shiftFrom" + System.lineSeparator());
			params.put("shiftFrom", searchDto.getShiftFrom());
		}
		if (searchDto.getShiftTo() != null && !searchDto.getShiftTo().isBlank()) {
			sql.append("AND shift.end_time = :shiftTo" + System.lineSeparator());
			params.put("shiftTo", searchDto.getShiftTo());
		}
		if (searchDto.getShiftId() != null && searchDto.getShiftId() != 0) {
			sql.append("AND ussa.shift_id = :shiftId" + System.lineSeparator());
			params.put("shiftId", searchDto.getShiftId());
		}
	
		if (searchDto.getTimeZoneId() != null && !searchDto.getTimeZoneId().isBlank()) {
			sql.append("AND shift.timie_zone = :timeZoneId" + System.lineSeparator());
			params.put("timeZoneId", searchDto.getTimeZoneId());
		}
		if (searchDto.getImageUploads() != null && !searchDto.getImageUploads().trim().isBlank()) {
			sql.append("AND ui.Image_Uploads = :ImageUploads" + System.lineSeparator());
			params.put("ImageUploads", searchDto.getImageUploads());
		}
	
		sql.append("ORDER BY ui.ID,shift.shift_id");
		Query query = entityManager.createNativeQuery(sql.toString(),"unit_search");

		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());

		}
		@SuppressWarnings("unchecked")
		List<UnitSearchResponseDto> resultList = query.getResultList();

		return resultList;
	}
}
