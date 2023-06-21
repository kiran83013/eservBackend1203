package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.CustomerPid;

public interface CustomerPidRepository extends JpaRepository<CustomerPid, Long>{
	
	@Query(value = "SELECT pid.ID AS id,pid.RefId AS refId,pid.Type AS type,pid.Value AS value,pid.FromDate AS fromDate,\r\n" + 
			"pid.ToDate AS toDate,pid.CreatedBy AS createdBy,pid.CreatedDate AS createdDate,pid.UpdatedBy AS updatedBy,\r\n" + 
			"pid.UpdatedDate AS updatedDate,pid.Status AS status,pid.Notes AS notes,pid.Url AS url, mpt.Name AS PidType,pid.OrganizationId AS organizationId,\r\n" + 
			"mo.Name AS organizationName\r\n" + 
			"FROM customer_pid pid\r\n" + 
			"LEFT JOIN master_document_type mpt ON mpt.ID = pid.Type \r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = pid.OrganizationId\r\n" + 
			"WHERE\r\n" + 
			"pid.RefId =?1\r\n" + 
			"AND pid.OrganizationId =?2",nativeQuery = true)
	List<Map<String, String>> findByRefIdAndOrganizationId(Long refId,Long organizationId);

//	List<CustomerPid> findByRefId(Long refId);

	@Query(value="SELECT pid.ID AS id,pid.RefId AS refId,pid.Type AS type,pid.Value AS value,pid.FromDate AS fromDate,\r\n" + 
			"pid.ToDate AS toDate,pid.CreatedBy AS createdBy,pid.CreatedDate AS createdDate,pid.UpdatedBy AS updatedBy, \r\n" + 
			"pid.UpdatedDate AS updatedDate,pid.Status AS status,pid.Notes AS notes,pid.Url AS url, mpt.Name AS PidType,pid.OrganizationId AS organizationId,\r\n" + 
			"mo.Name AS organizationName\r\n" + 
			"FROM customer_pid pid\r\n" + 
			"LEFT JOIN master_document_type mpt ON mpt.ID = pid.Type\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = pid.OrganizationId" ,nativeQuery = true)
	List<Map<String, String>> findAllList();

	
//	@Query(value = "SELECT e.ID, e.CreatedBy, e.CreatedDate, e.FromDate, e.Notes, e.RefId, ci.BusinessName, e.Status, e.ToDate, e.Type, e.UpdatedBy,\r\n" + 
//			"e.UpdatedDate, e.Url, e.Value, e.OrganizationId, mo.Name\r\n" + 
//			"FROM customer_pid e\r\n" + 
//			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = e.OrganizationId\r\n" + 
//			"LEFT JOIN customer_info AS ci ON ci.CUSTOMERID = e.RefId\r\n" + 
//			"WHERE e.OrganizationId = ?1\r\n" + 
//			"AND e.RefId = ?2", countQuery = "SELECT COUNT(*) FROM customer_pid e WHERE e.OrganizationId = ?1 AND e.RefId = ?2", nativeQuery = true)
	Page<CustomerPid> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);

	@Query(value=" SELECT c.name AS typeName FROM master_document_type c WHERE c.ID =?1",nativeQuery = true)
	Optional<String> getTypeName(Long type);

	@Query(value="SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =?1",nativeQuery = true)
	Optional<String> getBusinessName(Long refId);

	@Query(value="SELECT c.Name AS OrganizationName FROM master_organization c WHERE c.OrganizationId =?1",nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);

}
