package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.SlaHeaderModel;

public interface SlaHeaderRepository extends JpaRepository<SlaHeaderModel, Long>{

	@Query(value = "SELECT slah.id,\r\n" + 
			"slah.org_id AS orgId,\r\n" + 
			"mo.Name AS orgName,\r\n" + 
			"slah.business_unit AS businessUnit,\r\n" + 
			"slah.cost_center AS costCenter,\r\n" + 
			"slah.location,\r\n" + 
			"slah.business,\r\n" + 
			"slah.serivce_type AS serivceType,\r\n" + 
			"slah.shift_id AS shiftId,\r\n" + 
			"sm.shift_name AS shiftName,\r\n" + 
			"slah.sla_time AS slaTime,\r\n" + 
			"slah.note,\r\n" + 
			"slah.start_date AS startDate,\r\n" + 
			"slah.end_date AS endDate,\r\n" + 
			"slah.record_status AS recordStatus,\r\n" + 
			"slah.created_by AS createdBy,\r\n" + 
			"slah.created_date AS createdDate,\r\n" + 
			"slah.updated_by AS updatedBy,\r\n" + 
			"slah.updated_date AS updatedDate\r\n" + 
			"FROM sla_header AS slah\r\n" + 
			"LEFT JOIN shifts_manager AS sm ON sm.shift_id = slah.shift_id\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = slah.org_id\r\n" + 
			"WHERE\r\n" + 
			"slah.org_id = 1\r\n" + 
			"AND\r\n" + 
			"slah.record_status = \"Active\"", countQuery = "SELECT COUNT(*) FROM sla_header AS slah WHERE slah.org_id = 1 AND slah.record_status = \"Active\"",nativeQuery = true)
	Page<Map<String, String>> findByOrgIdLists(Long orgId, Pageable pageable);

	@Query(value = "SELECT slah.id,\r\n" + 
			"slah.org_id AS orgId,\r\n" + 
			"mo.Name AS orgName,\r\n" + 
			"slah.business_unit AS businessUnit,\r\n" + 
			"bu.Name AS businessUnitName,\r\n" + 
			"slah.cost_center AS costCenter,\r\n" + 
			"cc.Name AS costCenterName,\r\n" + 
			"slah.location,\r\n" + 
			"lo.Name AS locationName,\r\n" + 
			"slah.business,\r\n" + 
			"slah.serivce_type AS serivceType,\r\n" + 
			"sth.Name AS serivceTypeName,\r\n" + 
			"slah.shift_id AS shiftId,\r\n" + 
			"sm.shift_name AS shiftName,\r\n" + 
			"slah.sla_time AS slaTime,\r\n" + 
			"slah.note,\r\n" + 
			"slah.start_date AS startDate,\r\n" + 
			"slah.end_date AS endDate,\r\n" + 
			"slah.record_status AS recordStatus,\r\n" + 
			"slah.created_by AS createdBy,\r\n" + 
			"slah.created_date AS createdDate,\r\n" + 
			"slah.updated_by AS updatedBy,\r\n" + 
			"slah.updated_date AS updatedDate\r\n" + 
			"FROM sla_header AS slah\r\n" + 
			"LEFT JOIN business_unit AS bu ON bu.BusinessUnitId = slah.business_unit\r\n" + 
			"LEFT JOIN cost_center AS cc ON cc.CostCenterId = slah.cost_center\r\n" + 
			"LEFT JOIN location AS lo ON lo.LocationId = slah.location\r\n" + 
			"LEFT JOIN service_types_header AS sth ON sth.ID = slah.serivce_type\r\n" + 
			"LEFT JOIN shifts_manager AS sm ON sm.shift_id = slah.shift_id\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = slah.org_id\r\n" + 
			"WHERE\r\n" + 
			"slah.org_id = ?1\r\n" + 
			"AND\r\n" + 
			"slah.record_status = \"Active\"", nativeQuery = true)
	List<Map<String, String>> findByOrgIdList(Long orgId);

}
