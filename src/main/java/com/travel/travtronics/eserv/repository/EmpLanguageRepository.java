package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.EmpLanguage;

public interface EmpLanguageRepository extends JpaRepository<EmpLanguage, Long>{

//	List<EmpLanguage> findByRefId(Long refId);

	
	@Query(value = "SELECT e.*,l.Name AS languageName \r\n" + 
			"FROM emp_language e \r\n" + 
			"LEFT JOIN master_language l ON l.ID=e.LanguageId\r\n" + 
			"WHERE e.RefId=?1",nativeQuery = true)
	List<Map<String,String>> findByRefId(Long refId);

	List<EmpLanguage> findAllByOrganizationId(Long organizationId);

	Page<EmpLanguage> findByOrganizationId(Long organizationId, Pageable pageable);
	
	@Query(value =  "SELECT c.Name AS languageName  FROM master_language  c WHERE c.Id=?1", nativeQuery = true)
	Optional<String> getLanguageName(Long languageId);

	Page<EmpLanguage> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);

	@Query(value =  "SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =?1", nativeQuery = true)
	Optional<String> getBusinessName(Long refId);

	@Query(value =  "SELECT c.Name AS organizationName FROM master_organization c WHERE c.OrganizationId =?1", nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);

	

}
