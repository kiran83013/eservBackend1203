package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.SanctionGroup;

public interface SanctionGroupRepository extends JpaRepository<SanctionGroup, Long>{

	@Query(value = "SELECT sg.id,\r\n" + 
			"sg.name,\r\n" + 
			"sg.description,\r\n" + 
			"sg.org_id AS orgId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"sg.start_date AS startDate,\r\n" + 
			"sg.end_date AS endDate,\r\n" + 
			"sg.status,\r\n" + 
			"sg.created_by AS createdBy,\r\n" + 
			"sg.updated_by AS updatedBy,\r\n" + 
			"sg.created_date AS createdDate,\r\n" + 
			"sg.updated_date AS updatedDate\r\n" + 
			"FROM sanction_group AS sg\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = sg.org_id\r\n" + 
			"WHERE\r\n" + 
			"sg.org_id = ?1\r\n" + 
			"AND\r\n" + 
			"sg.status = \"Active\"", nativeQuery = true)
	List<Map<String, String>> findByOrgIdList(Long orgId);

	Page<SanctionGroup> findByOrgId(Long orgId, Pageable pageable);

	@Query(value = "SELECT sg.id,\r\n" + 
			"sg.name,\r\n" + 
			"sg.description,\r\n" + 
			"sg.org_id AS orgId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"sg.start_date AS startDate,\r\n" + 
			"sg.end_date AS endDate,\r\n" + 
			"sg.status,\r\n" + 
			"sg.created_by AS createdBy,\r\n" + 
			"sg.updated_by AS updatedBy,\r\n" + 
			"sg.created_date AS createdDate,\r\n" + 
			"sg.updated_date AS updatedDate\r\n" + 
			"FROM sanction_group AS sg\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = sg.org_id\r\n" + 
			"WHERE\r\n" + 
			"sg.org_id = ?1\r\n" + 
			"AND\r\n" + 
			"sg.status = \"Active\"\r\n" + 
			"AND\r\n" + 
			"sg.name LIKE %?2%", nativeQuery = true)
	Page<Map<String, String>> findByOrgIdAndSanctionNameOrBusinessName(Long orgId, String name, Pageable pageable);

}
