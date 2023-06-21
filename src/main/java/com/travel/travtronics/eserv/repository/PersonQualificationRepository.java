package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.PersonQualificationModel;

public interface PersonQualificationRepository extends JpaRepository<PersonQualificationModel, Long> {

	@Query(value = "SELECT pq.ID AS id , pq.CertificationNumber AS certificationNumber,\r\n"
			+ "pq.College AS college,pq.CreatedBy AS createdBy,pq.CreatedDate AS createdDate,\r\n"
			+ "pq.FromDate AS fromDate,pq.RefId AS refId,\r\n"
			+ "pq.Qualification AS qualification,pq.QualificationType AS qualificationType,\r\n"
			+ "mq.Name AS qualificationTypeName,\r\n"
			+ "pq.Status AS status,pq.ToDate AS toDate,pq.UpdatedBy AS updatedBy,\r\n"
			+ "pq.UpdatedDate AS updatedDate,pq.OrganizationId organizationId,org.Name OrganizationName\r\n"
			+ "FROM  person_qualification pq\r\n"
			+ "LEFT JOIN master_qualification_type mq ON mq.ID = pq.QualificationType\r\n"
			+ "LEFT JOIN  master_organization org ON org.OrganizationId=pq.OrganizationId\r\n" + "WHERE\r\n"
			+ "pq.RefId=?1 AND pq.OrganizationId=?2", nativeQuery = true)
	List<Map<String, String>> getQualificationByrefId(Long refId, Long orgId);
	
	@Query(value ="SELECT c.name AS qualificationTypeName FROM master_qualification_type c WHERE c.ID =?1",nativeQuery = true)
	Optional<String> getQualificationName(Long qualificationType);

	Page<PersonQualificationModel> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);

	@Query(value ="SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =?1",nativeQuery = true)
	Optional<String> getBusinessName(Long refId);

	@Query(value ="SELECT c.Name AS organizationName FROM master_organization c WHERE c.OrganizationId =?1",nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);
}
