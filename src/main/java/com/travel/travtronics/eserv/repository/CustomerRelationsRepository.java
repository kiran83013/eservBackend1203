package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.CustomerRelations;

public interface CustomerRelationsRepository extends JpaRepository<CustomerRelations, Long> {

	Optional<CustomerRelations> findByRelationShipId(Long relationShipId);

//	List<CustomerRelations> findByFirstPartyId(Long firstPartyId);
	
	/*@Query(value = "SELECT pr.RELATIONSHIPID AS relationShipId,pr.FirstPartyID AS firstPartyID,pr.SecondPartyID AS secondPartyID,pr.SecondPartyTypeID AS secondPartyTypeID,\r\n" + 
			"pr.FromRelationID AS fromRelationID,pr.ToRelationID AS toRelationID,pr.FromDate AS fromDate,pr.ToDate AS toDate,pr.Status AS status,pr.CreatedBy AS createdBy,\r\n" + 
			"pr.CreatedDate AS createdDate,pr.UpdatedBy AS updatedBy,pr.UpdatedDate AS updatedDate,rt.Name  AS fromRelationName,\r\n" + 
			"mrt.Name AS toRelationName,pp.BusinessName firstPartyName,ccr.BusinessName AS secondPartyBizName,pr.OrganizationId AS organizationId, \r\n" + 
			"mo.Name AS organizationName\r\n" + 
			"FROM customer_relations pr\r\n" + 
			"LEFT JOIN master_relation_type  rt ON rt.ID=pr.FromRelationID\r\n" + 
			"LEFT JOIN master_relation_type  mrt ON mrt.ID=pr.ToRelationID\r\n" + 
			"LEFT JOIN customer_info pp ON pp.CUSTOMERID=pr.FirstPartyID\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = pr.OrganizationId\r\n" + 
			"LEFT JOIN customer_info ccr ON pr.SecondPartyID=CASE WHEN pr.SecondPartyTypeID='biz' THEN ccr.CUSTOMERID ELSE NULL END\r\n" + 
			"#LEFT JOIN pax_profile pax ON pr.SecondPartyID=CASE WHEN pr.SecondPartyTypeID='pax' THEN pax.ID ELSE NULL END\r\n" + 
			"WHERE\r\n" + 
			"pr.FirstPartyID=?1 AND pr.OrganizationId =?2 AND pr.Status='Active'",nativeQuery = true)
	List<Map<String, String>> findByFirstPartyIdAndOrganizationId(Long firstPartyId,Long organizationId);*/

//	Page<CustomerRelations> findByOrganizationId(Long organizationId, Pageable pageable);
	
	@Query(value =  "SELECT c.BusinessName AS firstPartyIdName FROM customer_info  c WHERE c.CUSTOMERID=?1",nativeQuery = true)
	Optional<String> getFirstPartyName(Long firstPartyId);

	@Query(value =  "SELECT c.BusinessName AS secondPartyIdName FROM customer_info  c WHERE c.CUSTOMERID=?1",nativeQuery = true)
	Optional<String> getSecondPartyName(Long secondPartyId);

	@Query(value =  "SELECT c.Name AS fromRelationIdName FROM master_relation_type  c WHERE c.Id=?1",nativeQuery = true)
	Optional<String> getFromRelationName(Long fromRelationId);

	@Query(value =  "SELECT c.Name AS toRelationIdName FROM master_relation_type  c WHERE c.Id=?1",nativeQuery = true)
	Optional<String> getToRelationName(Long toRelationId);
	
	
	

	@Query(value = "SELECT pr.RELATIONSHIPID AS relationShipId,pr.FirstPartyID AS firstPartyID,pr.SecondPartyID AS secondPartyID,pr.SecondPartyTypeID AS secondPartyTypeID,\r\n" + 
			"pr.FromRelationID AS fromRelationID,pr.ToRelationID AS toRelationID,pr.FromDate AS fromDate,pr.ToDate AS toDate,pr.Status AS status,pr.CreatedBy AS createdBy,\r\n" + 
			"pr.CreatedDate AS createdDate,pr.UpdatedBy AS updatedBy,pr.UpdatedDate AS updatedDate,rt.Name  AS fromRelationName,\r\n" + 
			"mrt.Name AS toRelationName,pp.BusinessName firstPartyName,pp.BusinessName AS secondPartyName,ccr.BusinessName AS secondPartyBizName,pr.OrganizationId AS organizationId,\r\n" + 
			"mo.Name AS organizationName\r\n" + 
			"FROM customer_relations pr\r\n" + 
			"LEFT JOIN master_relation_type  rt ON rt.ID=pr.FromRelationID\r\n" + 
			"LEFT JOIN master_relation_type  mrt ON mrt.ID=pr.ToRelationID\r\n" + 
			"LEFT JOIN customer_info pp ON pp.CUSTOMERID=pr.FirstPartyID\r\n" + 
			"LEFT JOIN customer_info p ON p.CUSTOMERID=pr.SecondPartyID\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = pr.OrganizationId\r\n" + 
			"LEFT JOIN customer_info ccr ON pr.SecondPartyID=CASE WHEN pr.SecondPartyTypeID='biz' THEN ccr.CUSTOMERID ELSE NULL END\r\n" + 
			"#LEFT JOIN pax_profile pax ON pr.SecondPartyID=CASE WHEN pr.SecondPartyTypeID='pax' THEN pax.ID ELSE NULL END \r\n" + 
			"WHERE\r\n" + 
			"pr.FirstPartyID=?1 AND pr.OrganizationId =?2 AND pr.Status='Active'",nativeQuery = true)
	List<Map<String, String>> findByFirstPartyIdAndOrganizationId(Long firstPartyId,Long organizationId);

	Page<CustomerRelations> findByOrganizationIdAndFirstPartyId(Long organizationId, Long firstPartyId,
			Pageable pageable);

}
