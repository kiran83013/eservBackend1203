package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.SlaLinesModel;

public interface SlaLinesRepository extends JpaRepository<SlaLinesModel, Long>{

	List<SlaLinesModel> findByHeaderId(Long headerId);

	@Query(value = "SELECT slal.id,\r\n" + 
			"slal.header_id AS headerId,\r\n" + 
			"slal.org_id AS orgId,\r\n" + 
			"mo.Name AS orgName,\r\n" + 
			"slal.sla_name AS slaName,\r\n" + 
			"slal.shift_id AS shiftId,\r\n" + 
			"sm.shift_name AS shiftName,\r\n" + 
			"slal.sla_time AS slaTime,\r\n" + 
			"slal.note,\r\n" + 
			"slal.start_date AS startDate,\r\n" + 
			"slal.end_date AS endDate,\r\n" + 
			"slal.record_status AS recordStatus,\r\n" + 
			"slal.created_by AS createdBy,\r\n" + 
			"slal.created_date AS createdDate,\r\n" + 
			"slal.updated_by AS updatedBy,\r\n" + 
			"slal.updated_date AS updatedDate\r\n" + 
			"FROM sla_lines AS slal\r\n" + 
			"LEFT JOIN shifts_manager AS sm ON sm.shift_id = slal.shift_id\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = slal.org_id\r\n" + 
			"WHERE\r\n" + 
			"slal.org_id = ?1\r\n" + 
			"AND \r\n" + 
			"slal.record_status = \"Active\"", nativeQuery = true)
	List<Map<String, String>> findByOrgIdList(Long orgId);

	@Query(value = "SELECT slal.id,\r\n" + 
			"slal.header_id AS headerId,\r\n" + 
			"slal.org_id AS orgId,\r\n" + 
			"mo.Name AS orgName,\r\n" + 
			"slal.sla_name AS slaName,\r\n" + 
			"slal.shift_id AS shiftId,\r\n" + 
			"sm.shift_name AS shiftName,\r\n" + 
			"slal.sla_time AS slaTime,\r\n" + 
			"slal.note,\r\n" + 
			"slal.start_date AS startDate,\r\n" + 
			"slal.end_date AS endDate,\r\n" + 
			"slal.record_status AS recordStatus,\r\n" + 
			"slal.created_by AS createdBy,\r\n" + 
			"slal.created_date AS createdDate,\r\n" + 
			"slal.updated_by AS updatedBy,\r\n" + 
			"slal.updated_date AS updatedDate\r\n" + 
			"FROM sla_lines AS slal\r\n" + 
			"LEFT JOIN shifts_manager AS sm ON sm.shift_id = slal.shift_id\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = slal.org_id\r\n" + 
			"WHERE\r\n" + 
			"slal.org_id = ?1\r\n" + 
			"AND \r\n" + 
			"slal.record_status = \"Active\"", countQuery = "SELECT COUNT(*) FROM sla_lines AS slal WHERE slal.org_id = 1 AND slal.record_status = \"Active\"",nativeQuery = true)
	Page<Map<String, String>> findByOrgId(Long orgId, Pageable pageable);

}
