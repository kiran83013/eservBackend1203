package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.SanctionConfig;

public interface SanctionConfigRepository extends JpaRepository<SanctionConfig, Long>{

	
	@Query(value = "SELECT sc.Id AS id,\r\n" + 
			"sc.created_by AS createdBy,\r\n" + 
			"sc.created_date AS createdDate,\r\n" + 
			"sc.org_id AS orgId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"sc.business_unit AS businessUnit,\r\n" + 
			"bu.Name AS businessUnitName,\r\n" + 
			"sc.cost_center AS costCenter,\r\n" + 
			"cc.Name AS costCenterName,\r\n" + 
			"sc.location,\r\n" + 
			"lo.Name AS locationName,\r\n" + 
			"sc.description,\r\n" + 
			"sc.name,\r\n" + 
			"sc.sanction_group AS sanctionGroup,\r\n" + 
			"stg.name AS sanctionGroupName,\r\n" + 
			"sc.sanction_image AS sanctionImage,\r\n" + 
			"sc.sanction_view AS sanctionView,\r\n" + 
			"sc.sanction_view_value AS sanctionViewValue,\r\n" + 
			"sc.from_operator AS fromOperator,\r\n" + 
			"sc.from_value AS fromValue,\r\n" + 
			"sc.to_operator AS toOperator,\r\n" + 
			"sc.to_value AS toValue,\r\n" + 
			"sc.status,\r\n" + 
			"sc.type,\r\n" + 
			"sc.updated_by AS updatedBy,\r\n" + 
			"sc.updated_date AS updatedDate\r\n" + 
			"FROM sanction_config AS sc\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = sc.org_id\r\n" + 
			"LEFT JOIN sanction_group AS stg ON stg.Id = sc.sanction_group\r\n" + 
			"LEFT JOIN business_unit AS bu ON bu.BusinessUnitId = sc.business_unit\r\n" + 
			"LEFT JOIN cost_center AS cc ON cc.CostCenterId = sc.cost_center\r\n" + 
			"LEFT JOIN location AS lo ON lo.LocationId = sc.location\r\n" + 
			"WHERE\r\n" + 
			"sc.org_id = ?1\r\n" + 
			"AND\r\n" + 
			"sc.status = \"Active\"", nativeQuery = true)
	List<Map<String, String>> findByOrgId(Long orgId);

	@Query(value = "SELECT sc.Id AS id,\r\n" + 
			"sc.created_by AS createdBy,\r\n" + 
			"sc.created_date AS createdDate,\r\n" + 
			"sc.org_id AS orgId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"sc.business_unit AS businessUnit,\r\n" + 
			"bu.Name AS businessUnitName,\r\n" + 
			"sc.cost_center AS costCenter,\r\n" + 
			"cc.Name AS costCenterName,\r\n" + 
			"sc.location,\r\n" + 
			"lo.Name AS locationName,\r\n" + 
			"sc.description,\r\n" + 
			"sc.name,\r\n" + 
			"sc.sanction_group AS sanctionGroup,\r\n" + 
			"stg.name AS sanctionGroupName,\r\n" + 
			"sc.sanction_image AS sanctionImage,\r\n" + 
			"sc.sanction_view AS sanctionView,\r\n" + 
			"sc.sanction_view_value AS sanctionViewValue,\r\n" + 
			"sc.from_operator AS fromOperator,\r\n" + 
			"sc.from_value AS fromValue,\r\n" + 
			"sc.to_operator AS toOperator,\r\n" + 
			"sc.to_value AS toValue,\r\n" + 
			"sc.status,\r\n" + 
			"sc.type,\r\n" + 
			"sc.updated_by AS updatedBy,\r\n" + 
			"sc.updated_date AS updatedDate\r\n" + 
			"FROM sanction_config AS sc\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = sc.org_id\r\n" + 
			"LEFT JOIN sanction_group AS stg ON stg.Id = sc.sanction_group\r\n" + 
			"LEFT JOIN business_unit AS bu ON bu.BusinessUnitId = sc.business_unit\r\n" + 
			"LEFT JOIN cost_center AS cc ON cc.CostCenterId = sc.cost_center\r\n" + 
			"LEFT JOIN location AS lo ON lo.LocationId = sc.location\r\n" + 
			"WHERE\r\n" + 
			"sc.org_id = ?1\r\n" + 
			"AND\r\n" + 
			"sc.status = \"Active\"",countQuery = "SELECT COUNT(*) FROM sanction_config AS sc WHERE sc.org_id = ?1 AND sc.status = \"Active\"", nativeQuery = true)
	Page<Map<String, String>> findByOrgId(Long orgId, Pageable pageable);
	
	@Query(value = "SELECT sc.Id AS id,\r\n" + 
			"sc.created_by AS createdBy,\r\n" + 
			"sc.created_date AS createdDate,\r\n" + 
			"sc.org_id AS orgId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"sc.business_unit AS businessUnit,\r\n" + 
			"bu.Name AS businessUnitName,\r\n" + 
			"sc.cost_center AS costCenter,\r\n" + 
			"cc.Name AS costCenterName,\r\n" + 
			"sc.location,\r\n" + 
			"lo.Name AS locationName,\r\n" + 
			"sc.description,\r\n" + 
			"sc.name,\r\n" + 
			"sc.sanction_group AS sanctionGroup,\r\n" + 
			"stg.name AS sanctionGroupName,\r\n" + 
			"sc.sanction_image AS sanctionImage,\r\n" + 
			"sc.sanction_view AS sanctionView,\r\n" + 
			"sc.sanction_view_value AS sanctionViewValue,\r\n" + 
			"sc.from_operator AS fromOperator,\r\n" + 
			"sc.from_value AS fromValue,\r\n" + 
			"sc.to_operator AS toOperator,\r\n" + 
			"sc.to_value AS toValue,\r\n" + 
			"sc.status,\r\n" + 
			"sc.type,\r\n" + 
			"sc.updated_by AS updatedBy,\r\n" + 
			"sc.updated_date AS updatedDate\r\n" + 
			"FROM sanction_config AS sc\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = sc.org_id\r\n" + 
			"LEFT JOIN sanction_group AS stg ON stg.Id = sc.sanction_group\r\n" + 
			"LEFT JOIN business_unit AS bu ON bu.BusinessUnitId = sc.business_unit \r\n" + 
			"LEFT JOIN cost_center AS cc ON cc.CostCenterId = sc.cost_center\r\n" + 
			"LEFT JOIN location AS lo ON lo.LocationId = sc.location\r\n" + 
			"WHERE\r\n" + 
			"sc.org_id = ?1\r\n" + 
			"AND\r\n" + 
			"sc.status = \"Active\"\r\n" + 
			"AND (sc.name LIKE %?2% OR stg.name LIKE %?2%)", nativeQuery = true)
	Page<Map<String, String>> findByOrgIdAndSanctionNameOrBusinessName(Long orgId, String name,Pageable pageable);

}
