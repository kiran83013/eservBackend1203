package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.PersonVerificationModel;

public interface PersonVerificationRepository extends JpaRepository<PersonVerificationModel, Long> {

	@Query(value = "SELECT pv.ID AS id,pv.CreatedBy AS createdBy,pv.CreatedDate AS cretaedDate,pv.FromDate AS fromDate,\r\n"
			+ "pv.RefId AS refId,pv.Status AS status,pv.ToDate AS toDate,\r\n"
			+ "mvt.Name AS typeName,pv.Type AS type,pv.UpdatedBy AS updatedBy,pv.Value AS value,pv.OrganizationId organizationId,org.Name OrganizationName\r\n"
			+ "FROM person_verification pv  LEFT JOIN master_verification_type mvt ON mvt.ID = pv.Type\r\n"
			+ "LEFT JOIN  master_organization org ON org.OrganizationId=pv.OrganizationId\r\n"
			+ "WHERE  pv.RefId =?1 AND pv.OrganizationId=?2", nativeQuery = true)
	List<Map<String, String>> getVerificationByRefId(Long id, Long orgId);
	
	@Query(value = " SELECT c.name AS verficationTypeName FROM master_verification_type c WHERE c.ID =?1",nativeQuery = true)
	Optional<String> getTypeName(Long type);

	@Query(value = "SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =?1",nativeQuery = true)
	Optional<String> getBusinessName(Long refId);

	@Query(value = "SELECT c.Name AS organizationName FROM master_organization c WHERE c.OrganizationId =?1",nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);

	Page<PersonVerificationModel> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);
}
