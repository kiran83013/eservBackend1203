package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.Localization;

public interface LocalizationRepository extends JpaRepository<Localization, Long>{

	Page<Localization> findByOrganizationId(Long organizationId, Pageable pageable);

	@Query(value = "SELECT * FROM a3m_acc_role_type WHERE id = ?", nativeQuery = true)
	List<Map<String, String>> findByRoleTypeId(Long roleTypeId);

	@Query(value = "SELECT * FROM a3m_acc_role_type", nativeQuery = true)
	List<Map<String, String>> findAllList();

	@Query(value = "SELECT\r\n" + 
			"esr.id,\r\n" + 
			"esr.role_type_id,\r\n" + 
			"esr.key,\r\n" + 
			"esr.name,\r\n" + 
			"esr.description,\r\n" + 
			"esr.suspendedon,\r\n" + 
			"esr.is_system\r\n" + 
			"FROM `e-services-cp-users`.`a3m_acl_role` AS esr\r\n" + 
			"LEFT JOIN `e_services`.`a3m_acc_role_type` m ON m.id = esr.role_type_id\r\n" + 
			"WHERE\r\n" + 
			"esr.role_type_id = ?1", nativeQuery = true)
	List<Map<String, String>> findAllRolesList(Long roleTypeId);

	@Query(value = "SELECT c.Name AS organizationName  FROM `e_services`.`master_organization`  c WHERE c.OrganizationId= ?", nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);

	@Query(value = "SELECT ro.name AS roleName  FROM `e-services-cp-users`.`a3m_acl_role`  ro WHERE ro.id IN ?", nativeQuery = true)
	List<String> getRoleName(List<String> list);

	@Query(value = "SELECT rt.name AS roleTypeName  FROM `e_services`.`a3m_acc_role_type`  rt WHERE rt.id=1001", nativeQuery = true)
	Optional<String> getRoleTypeName(String roleType);
	
	 @Query(value = "SELECT lo.Id AS id,\r\n" + 
	 		"lo.OrganizationId AS organizationId,\r\n" + 
	 		"lo.RoleType AS roleType,\r\n" + 
	 		"rt.name AS roleTypeName,\r\n" + 
	 		"lo.Language AS `language`,ml.name AS LanguageName,\r\n" + 
	 		"lo.Role AS `role`,\r\n" + 
	 		"#ro.name AS roleName,\r\n" + 
	 		"(SELECT GROUP_CONCAT(DISTINCT r.name ORDER BY r.id) FROM `e-services-cp-users`.`a3m_acl_role` r WHERE FIND_IN_SET(r.id,lo.Role)) AS roleNames,\r\n" + 
	 		"lo.Status AS `status`,\r\n" + 
	 		"lo.KeyTrnsl AS keyTrnsl,\r\n" + 
	 		"lo.ValueTrnsl AS valueTrnsl,\r\n" + 
	 		"lo.CreatedBy AS createdBy,\r\n" + 
	 		"lo.CreatedDate AS createdDate,\r\n" + 
	 		"lo.UpdatedBy AS updatedBy,\r\n" + 
	 		"lo.UpdatedDate AS updatedDate\r\n" + 
	 		"FROM `e_services`.`master_localization` AS lo\r\n" + 
	 		"LEFT JOIN `e_services`.`a3m_acc_role_type` AS rt ON rt.id = lo.RoleType\r\n" + 
	 		"LEFT JOIN `e_services`.master_language AS ml ON ml.ID = lo.Language\r\n" + 
	 		"#LEFT JOIN `e-services-cp-users`.`a3m_acl_role` AS ro ON ro.id = lo.Role\r\n" + 
	 		"LEFT JOIN `e_services`.`master_organization` AS o ON o.OrganizationId = lo.OrganizationId\r\n" + 
	 		"WHERE lo.OrganizationId = ?1",
			    countQuery = "SELECT COUNT(*)\r\n" + 
			    		"\r\n" + 
			    		"FROM `e_services`.`master_localization` AS lo\r\n" + 
			    		"LEFT JOIN `e_services`.`a3m_acc_role_type` AS rt ON rt.id = lo.RoleType\r\n" + 
			    		"LEFT JOIN `e_services`.master_language AS ml ON ml.ID = lo.Language\r\n" + 
			    		"#LEFT JOIN `e-services-cp-users`.`a3m_acl_role` AS ro ON ro.id = lo.Role\r\n" + 
			    		"LEFT JOIN `e_services`.`master_organization` AS o ON o.OrganizationId = lo.OrganizationId\r\n" + 
			    		"WHERE lo.OrganizationId = ?1",
			    nativeQuery = true)
			  Page<Map<String, String>> getOrganizationId(Long organizationId, Pageable pageable);

	
}
