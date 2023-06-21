package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.SanctionPartyRelation;

public interface SanctionPartyRelationRepository extends JpaRepository<SanctionPartyRelation, Long> {

	@Query(value = "SELECT sg.id,\r\n" + 
			"sg.orgid AS orgId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"sg.sanctionid AS sanctionId,\r\n" + 
			"sc.name AS sanctionName,\r\n" + 
			"sg.bizid AS bizId,\r\n" + 
			"ci.BusinessName AS businessName,\r\n" + 
			"sg.valid_from_date AS validFromDate,\r\n" + 
			"sg.valid_to_date AS validToDate,\r\n" + 
			"sg.status,\r\n" + 
			"sg.created_by AS createdBy,\r\n" + 
			"sg.updated_by AS updatedBy,\r\n" + 
			"sg.created_date AS createdDate,\r\n" + 
			"sg.updated_date AS updatedDate\r\n" + 
			"FROM sanction_party_relation AS sg\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = sg.orgid\r\n" + 
			"LEFT JOIN sanction_config AS sc ON sc.id = sg.sanctionid\r\n" + 
			"LEFT JOIN customer_info AS ci ON ci.CUSTOMERID = sg.bizid\r\n" + 
			"WHERE\r\n" + 
			"sg.orgid = ?1\r\n" + 
			"AND\r\n" + 
			"sg.status = 1", nativeQuery = true)
	List<Map<String, String>> findByOrgIdList(Long orgId);

	@Query(value = "SELECT sg.id,\r\n" + 
			"sg.orgid AS orgId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"sg.sanctionid AS sanctionId,\r\n" + 
			"sc.name AS sanctionName,\r\n" + 
			"sg.bizid AS bizId,\r\n" + 
			"ci.BusinessName AS businessName,\r\n" + 
			"sg.valid_from_date AS validFromDate,\r\n" + 
			"sg.valid_to_date AS validToDate,\r\n" + 
			"sg.status,\r\n" + 
			"sg.created_by AS createdBy,\r\n" + 
			"sg.updated_by AS updatedBy,\r\n" + 
			"sg.created_date AS createdDate,\r\n" + 
			"sg.updated_date AS updatedDate\r\n" + 
			"FROM sanction_party_relation AS sg\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = sg.orgid\r\n" + 
			"LEFT JOIN sanction_config AS sc ON sc.id = sg.sanctionid\r\n" + 
			"LEFT JOIN customer_info AS ci ON ci.CUSTOMERID = sg.bizid\r\n" + 
			"WHERE\r\n" + 
			"sg.orgid = 1\r\n" + 
			"AND\r\n" + 
			"sg.status = 1",countQuery = "SELECT COUNT(*) FROM sanction_party_relation AS sc WHERE sc.orgid = ?1 AND sc.status = 1", nativeQuery = true)
	Page<Map<String, String>> findByOrgId(Long orgId, Pageable pageable);
	
//	@Query(value = "SELECT sg.id, sg.orgid AS orgId, mo.Name AS organizationName, sg.sanctionid AS sanctionId, " 
//	        + "sc.name AS sanctionName, sg.bizid AS bizId, ci.BusinessName AS businessName, sg.valid_from_date AS validFromDate, "
//	        + "sg.valid_to_date AS validToDate, sg.status, sg.created_by AS createdBy, sg.updated_by AS updatedBy, "
//	        + "sg.created_date AS createdDate, sg.updated_date AS updatedDate "
//	        + "FROM sanction_party_relation sg "
//	        + "LEFT JOIN master_organization mo ON mo.OrganizationId = sg.orgid "
//	        + "LEFT JOIN sanction_config sc ON sc.id = sg.sanctionid "
//	        + "LEFT JOIN customer_info ci ON ci.CUSTOMERID = sg.bizid "
//	        + "WHERE sg.orgid = :orgId AND sg.status = 1 AND ci.BusinessName LIKE %:name% ", 
//	        countQuery = "SELECT COUNT(*) FROM sanction_party_relation sc WHERE sc.orgid = :orgId AND sc.status = 1", 
//	        nativeQuery = true)
//	Page<Map<String, String>> findByOrgId(@Param("orgId") Long orgId, @Param("name") String name, Pageable pageable);
	
	@Query(value = "SELECT sg.id, sg.orgid AS orgId, mo.Name AS organizationName, sg.sanctionid AS sanctionId, sc.name AS sanctionName, sg.bizid AS bizId, ci.BusinessName AS businessName, sg.valid_from_date AS validFromDate, sg.valid_to_date AS validToDate, sg.status, sg.created_by AS createdBy, sg.updated_by AS updatedBy, sg.created_date AS createdDate, sg.updated_date AS updatedDate\n" +
            "FROM sanction_party_relation AS sg\n" +
            "LEFT JOIN master_organization AS mo ON mo.OrganizationId = sg.orgid\n" +
            "LEFT JOIN sanction_config AS sc ON sc.id = sg.sanctionid\n" +
            "LEFT JOIN customer_info AS ci ON ci.CUSTOMERID = sg.bizid\n" +
            "WHERE sg.orgid = ?1 AND sg.status = 1\n" +
            "AND (sc.name LIKE %?2% OR ci.BusinessName LIKE %?2%)", nativeQuery = true)
    Page<Map<String, String>> findByOrgIdAndSanctionNameOrBusinessName(Long orgId, String name, Pageable pageable);


}
