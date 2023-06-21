package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.EmpVerification;

public interface EmpVerificationRepository extends JpaRepository<EmpVerification, Long> {

//	List<EmpVerification> findByRefId(Long refId);

	@Query(value = "SELECT pv.*,mvt.Name AS TypeName, mo.name AS OrganizationName\r\n" + "FROM emp_verification pv\r\n"
			+ "LEFT JOIN master_verification_type mvt ON mvt.ID = pv.Type \r\n"
			+ "INNER JOIN master_organization AS mo ON mo.OrganizationId = pv.OrganizationId\r\n" + "WHERE\r\n"
			+ "pv.RefId =?1", nativeQuery = true)
	List<Map<String, String>> findAllByRefId(Long refId);

	@Query(value = "SELECT ev.ID AS id,ev.RefId AS refId,ev.Type AS type,mpt.Name AS typeName,ev.Value AS value,ev.FromDate AS fromDate,\r\n"
			+ "ev.ToDate AS toDate,ev.CreatedBy AS createdBy,ev.CreatedDate AS createdDate,ev.UpdatedBy AS updatedBy, \r\n"
			+ "ev.UpdatedDate AS updatedDate,ev.Status AS status,ev.OrganizationId AS organizationId,\r\n"
			+ "mo.Name AS organizationName\r\n" + "FROM emp_verification ev\r\n"
			+ "LEFT JOIN master_verification_type mpt ON mpt.ID = ev.Type\r\n"
			+ "LEFT JOIN master_organization AS mo ON mo.OrganizationId = ev.OrganizationId\r\n"
			+ "WHERE  ev.OrganizationId =?1", nativeQuery = true)
	List<Map<String, String>> findAllByOrganizationId(Long organizationId);

	@Query("select count(*) from EmpVerification h where h.organizationId=?1")
	Long countByOrganizationIdAndRefId(Long organizationId);

	@Query("select count(*) from EmpVerification h")
	Long countAll();

	List<EmpVerification> findByOrganizationIdAndRefId(Long organizationId, Long refId);

}
