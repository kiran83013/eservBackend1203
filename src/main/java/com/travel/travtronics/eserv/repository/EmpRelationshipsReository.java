package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.EmpRelationships;

public interface EmpRelationshipsReository extends JpaRepository<EmpRelationships, Long>{

	Optional<EmpRelationships> findByRelationShipId(Long relationShipId);

//	List<EmpRelationships> findByFirstPartyId(Long firstPartyId);
	
	@Query(value = "SELECT pr.RELATIONSHIPID AS relationShipId,\r\n" + 
			"pr.FirstPartyID AS firstPartyId,\r\n" + 
			"pr.SecondPartyID AS secondPartyId,\r\n" + 
			"pr.FromRelationID AS fromRelationId,rt.Name AS fromRelationName,\r\n" + 
			"pr.ToRelationID AS toRelationId,mrt.Name AS toRelationName,\r\n" + 
			"pr.FromDate AS fromdate,pr.ToDate AS toDate,pr.Status AS status ,\r\n" + 
			"pr.CreatedDate AS createdDate,pr.CreatedBy AS createdBy,\r\n" + 
			"pp.FirstName AS firstPartyName,pr.OrganizationId AS organizationId,mo.Name AS organizationName,\r\n" + 
			"pr.UpdatedBy AS updatedBy,pr.UpdatedDate AS updatedDate,pr.SecondPartyTypeID AS secondPartyTypeId,\r\n" + 
			"CONCAT(emp.FirstName,'.',emp.LastName) AS secondPartyEmpName,cust.BusinessName AS secondPartyBizName\r\n" + 
			"FROM emp_relationships pr\r\n" + 
			"LEFT JOIN master_relation_type  rt ON rt.ID=pr.FromRelationID\r\n" + 
			"LEFT JOIN master_relation_type  mrt ON mrt.ID=pr.ToRelationID\r\n" + 
			"LEFT JOIN emp_profile pp ON pp.ID=pr.FirstPartyID\r\n" + 
			"INNER JOIN master_organization AS mo ON mo.OrganizationId = pr.OrganizationId\r\n" + 
			"LEFT JOIN emp_profile emp ON pr.SecondPartyID=CASE WHEN pr.SecondPartyTypeID='emp' THEN emp.ID ELSE NULL END\r\n" + 
			"LEFT JOIN customer_info cust ON pr.SecondPartyID=CASE WHEN pr.SecondPartyTypeID='biz' THEN cust.CUSTOMERID ELSE NULL END\r\n" + 
			"WHERE\r\n" + 
			"pr.FirstPartyID=?1 AND pr.Status='Active'",nativeQuery = true)
	List<Map<String, String>> findByFirstPartyId(Long firstPartyId);
	
	@Query(value ="SELECT c.FirstName AS firstPartyName FROM emp_profile  c WHERE c.id=?1",nativeQuery = true)
	Optional<String> getFirstPartyName(Long firstPartyId);
	
	@Query(value ="SELECT c.BusinessName AS secondPartyName FROM customer_info  c WHERE c.CUSTOMERID=?1",nativeQuery = true)
	Optional<String> getSecondPartyName(Long secondPartyId);
	
	@Query(value = "SELECT c.Name AS fromRelationIdName FROM master_relation_type  c WHERE c.Id=?1",nativeQuery = true)
	Optional<String> getFromRelationName(Long fromRelationId);
	
	@Query(value = "SELECT c.Name AS toRelationIdName FROM master_relation_type  c WHERE c.Id=?1",nativeQuery = true)
	Optional<String> getToRelationName(Long toRelationId);

	Page<EmpRelationships> findByOrganizationIdAndFirstPartyId(Long organizationId, Long firstPartyId,
			Pageable pageable);

}
