package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.PersonSocialModel;

public interface PersonSocialRepository extends JpaRepository<PersonSocialModel, Long> {

	@Query(value = "SELECT ps.ID AS id,ps.CreatedBy AS createdBy,ps.CreatedDate AS createdDate,ps.FromDate AS fromDate,\r\n"
			+ "ps.ToDate AS toDate,ps.Status AS status,ps.Type AS type,ps.Value AS value,ps.UpdatedBy AS updatedBy,\r\n"
			+ "ps.UpdatedDate AS updatedDate,msm.Name AS socialName,ps.OrganizationId  organizationId,\r\n"
			+ "org.Name AS  organizationName\r\n" + "FROM person_social ps\r\n"
			+ "LEFT JOIN master_social_media_type msm ON msm.ID = ps.Type\r\n"
			+ "LEFT JOIN master_organization org ON org.OrganizationId=ps.OrganizationId\r\n" + "WHERE \r\n"
			+ "ps.RefId =?1 AND ps.OrganizationId=?2", nativeQuery = true)
	List<Map<String, String>> getSocialByRefId(Long refId, Long orgId);
	
	@Query (value = " SELECT c.name AS typeName FROM master_social_media_type c WHERE c.ID =?1",nativeQuery = true)
	Optional<String> getTypeName(Long type);

	Page<PersonSocialModel> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);

	@Query (value = "SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =?1",nativeQuery = true)
	Optional<String> getBusinessName(Long refId);

	@Query (value = "SELECT c.Name AS organizationName FROM master_organization c WHERE c.OrganizationId =?1",nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);
}
