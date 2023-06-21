package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.EmpSocial;

public interface EmpSocialRepository extends JpaRepository<EmpSocial, Long>{
	
	@Query(value = "SELECT ps.*,  msm.Name AS SocialName, mo.name AS OrganizationName\r\n" + 
			"FROM emp_social ps\r\n" + 
			"INNER JOIN master_organization AS mo ON mo.OrganizationId = ps.OrganizationId\r\n" + 
			"LEFT JOIN master_social_media_type msm ON msm.ID = ps.Type\r\n" + 
			"WHERE \r\n" + 
			"ps.RefId =?1",nativeQuery = true)
	List<Map<String, String>> findAllByRefId(Long refId);

	List<EmpSocial> findAllByOrganizationId(Long organizationId);

	@Query(value = "SELECT c.Name AS socialMediaTypeName  FROM master_social_media_type  c WHERE c.Id=?1",nativeQuery = true)
	Optional<String> getSocialMediaTypeName(Long type);

	Page<EmpSocial> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);

	@Query(value = "SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =1", nativeQuery = true)
	Optional<String> getBusinessName(Long refId);

	@Query(value = "SELECT c.Name AS organizationName FROM master_organization c WHERE c.OrganizationId =?1", nativeQuery = true)
	Optional<String> getorganizationName(Long organizationId);

}
