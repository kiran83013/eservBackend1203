package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.EmpPid;

public interface EmpPidRepository extends JpaRepository<EmpPid, Long>{

//	List<EmpPid> findByRefId(Long refId);
	
	@Query(value = "SELECT pid.*, mpt.Name AS PidType \r\n" + 
			"FROM emp_pid pid \r\n" + 
			"LEFT JOIN master_document_type mpt ON mpt.ID = pid.Type \r\n" + 
			"WHERE pid.RefId = ?1",nativeQuery = true)
	List<Map<String, String>> getAllListByEmpRefId(Long refId);

	List<EmpPid> findAllByOrganizationId(Long organizationId);
	
	@Query(value ="SELECT c.Name AS socialMediaTypeName  FROM master_social_media_type  c WHERE c.Id=?1",nativeQuery = true)
	Optional<String> getTypeName(Long type);

	Page<EmpPid> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);

	@Query(value ="SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =?1",nativeQuery = true)
	Optional<String> getBusinessName(Long refId);

	@Query(value ="SELECT c.Name AS organizationName FROM master_organization c WHERE c.OrganizationId =?1",nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);




}
