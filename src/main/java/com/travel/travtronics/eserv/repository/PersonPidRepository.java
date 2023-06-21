package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.PersonPidModel;

public interface PersonPidRepository extends JpaRepository<PersonPidModel, Long> {

	@Query(value = "SELECT pid.ID AS id,pid.CreatedBy AS createdBy,pid.CreatedDate AS createdDate, mpt.Name AS PidType,\r\n"
			+ "pid.FromDate AS fromDate,pid.Notes AS notes,pid.RefId AS refId,pid.Status AS status,pid.ToDate AS toDate,\r\n"
			+ "pid.Type AS type,pid.UpdatedBy AS updatedBy,pid.UpdatedDate AS updatedDate,pid.Url AS url,pid.Value AS value,\r\n"
			+ "pid.OrganizationId organizationId,org.Name AS organizationName\r\n"
			+ "FROM person_pid pid LEFT JOIN master_document_type mpt ON mpt.ID = pid.Type\r\n"
			+ "LEFT JOIN master_organization org ON org.OrganizationId=pid.OrganizationId\r\n"
			+ "WHERE pid.RefId =?1 AND pid.OrganizationId=?2", nativeQuery = true)
	List<Map<String, String>> getPidByRefId(Long refId, Long orgId);
	
	@Query(value = " SELECT c.name AS typeName FROM master_document_type c WHERE c.ID =?1",nativeQuery = true)
	Optional<String> getTypeName(Long type);

	Page<PersonPidModel> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);

	@Query(value = "SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =?1",nativeQuery = true)
	Optional<String> getBusinessName(Long refId);

	@Query(value = "SELECT c.Name AS organizationName FROM master_organization c WHERE c.OrganizationId =?1",nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);

}
