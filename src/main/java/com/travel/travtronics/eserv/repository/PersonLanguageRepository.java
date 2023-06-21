package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.PersonLanguageModel;

public interface PersonLanguageRepository extends JpaRepository<PersonLanguageModel, Long> {

	@Query(value = "SELECT e.ID AS id,\r\n" + "e.CreatedBy AS createdBy,e.CreatedDate AS createdDate,\r\n"
			+ "e.LanguageId AS languageId,\r\n" + "e.LanguageRead AS languageRead,\r\n"
			+ "e.LanguageWrite AS languageWrite,\r\n"
			+ "l.Name AS languageName,e.RefId AS refId,e.Speak AS speak,e.Status AS status,e.UpdatedBy AS updatedBy,e.UpdatedDate AS updatedDate,\r\n"
			+ "e.OrganizationId  organizationId,org.Name organizationName\r\n"
			+ "FROM person_language e LEFT JOIN master_language l ON l.ID=e.LanguageId\r\n"
			+ "LEFT JOIN master_organization org ON org.OrganizationId=e.OrganizationId\r\n"
			+ "WHERE e.RefId=?1 AND e.OrganizationId=?2", nativeQuery = true)
	List<Map<String, String>> getLanguageByrefId(Long refId, Long orgId);
	
	@Query(value =" SELECT c.name AS languageName FROM master_language c WHERE c.ID =?1",nativeQuery = true)
	Optional<String> getLanguageName(Long languageId);

	Page<PersonLanguageModel> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);

	@Query(value ="SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =?1",nativeQuery = true)
	Optional<String> getBusinessName(Long refId);

	@Query(value ="SELECT c.Name AS organizationName FROM master_organization c WHERE c.OrganizationId =?1",nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);
}
