package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.EmpQualification;

public interface EmpQualificationRepository extends JpaRepository<EmpQualification, Long>{
	
	@Query(value = "SELECT pq.ID AS id , pq.CertificationNumber AS certificationNumber,\r\n" + 
			"pq.College AS college,pq.CreatedBy AS createdBy,pq.CreatedDate AS createdDate,\r\n" + 
			"pq.FromDate AS fromDate,pq.RefId AS refId,\r\n" + 
			"pq.Qualification AS qualification,pq.QualificationType AS qualificationType,\r\n" + 
			"mq.Name AS qualificationTypeName,\r\n" + 
			"pq.Status AS STATUS,pq.ToDate AS toDate,pq.UpdatedBy AS updatedBy,\r\n" + 
			"pq.UpdatedDate AS updatedDate FROM  emp_qualification pq\r\n" + 
			"LEFT JOIN master_qualification_type mq ON mq.ID = pq.QualificationType WHERE\r\n" + 
			"pq.RefId= ?1",nativeQuery = true)
	List<Map<String, String>> findAllByRefId(Long refId);

	List<EmpQualification> findAllByOrganizationId(Long organizationId);

	@Query(value = "SELECT c.Name AS qualificationTypeName FROM master_qualification_type  c WHERE c.Id=?1",nativeQuery = true)
	Optional<String> getQualificationtypeName(Long qualificationType);

	Page<EmpQualification> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);

	@Query(value = "SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =?1",nativeQuery = true)
	Optional<String> getBusinessName(Long refId);

	@Query(value = "SELECT c.Name AS organizationName FROM master_organization c WHERE c.OrganizationId =?1",nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);

//	List<EmpQualification> findByRefId(Long refId);

}
