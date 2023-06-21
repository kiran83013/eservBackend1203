package com.travel.travtronics.eserv.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.enums.Status;
import com.travel.travtronics.eserv.model.LeadInterestedProperties;

public interface LeadInterestedPropertiesRepository extends JpaRepository<LeadInterestedProperties, Long>{

	@Query(value = "SELECT\r\n" + 
			"lip.ID AS id,\r\n" + 
			"lip.UnitId AS unitId,\r\n" + 
			"ui.Unit_Name AS unitName,\r\n" + 
			"lip.BusinessId AS businessId,\r\n" + 
			"ci.BusinessName AS businessName,\r\n" + 
			"lip.OrganizationId AS organizationId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"lip.Images AS images,\r\n" + 
			"lip.Status AS status,\r\n" + 
			"lip.CreatedBy AS createdBy,\r\n" + 
			"lip.CreatedDate AS createdDate,\r\n" + 
			"lip.UpdatedBy AS updatedBy,\r\n" + 
			"lip.UpdatedDate AS updatedDate,\r\n" + 
			"lip.CreatedChannel AS createdChannel,\r\n" + 
			"lip.UpdatedChannel AS UpdatedChannel\r\n" + 
			"FROM lead_interested_properties AS lip\r\n" + 
			"LEFT JOIN unit_information AS ui ON ui.ID = lip.UnitId\r\n" + 
			"LEFT JOIN customer_info AS ci ON ci.CUSTOMERID = lip.BusinessId\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = lip.OrganizationId\r\n" + 
			"WHERE lip.BusinessId = ?1\r\n" + 
			"AND lip.OrganizationId = ?2\r\n" + 
			"AND lip.Status = \"Active\"", countQuery = "SELECT COUNT(*) FROM lead_interested_properties AS lip WHERE lip.BusinessId = 183 AND lip.OrganizationId = 1 AND lip.Status = \"Active\"", nativeQuery = true)
	Page<Map<String, String>> findAllByBusinessId(Long businessId, Long organizationId, Pageable pageable);

	Page<LeadInterestedProperties> findByOrganizationIdAndBusinessIdAndStatus(Long organizationId, Long businessId,
			Status active, Pageable pageable);

	@Query(value ="SELECT c.BusinessName AS businessName FROM customer_info c WHERE c.CUSTOMERID =?1",nativeQuery = true)
	Optional<String> getBusinessName(Long businessName);

	@Query(value ="SELECT c.Name AS organizationName FROM master_organization c WHERE c.OrganizationId =?1",nativeQuery = true)
	Optional<String> getOrganizationName(Long organizationId);

	@Query(value ="SELECT c.Unit_Name AS unitName FROM unit_information c WHERE c.ID = ?1",nativeQuery = true)
	Optional<String> getUnitName(Long unitName);

	int countByUnitIdAndBusinessIdAndOrganizationId(Long unitId, Long businessId, Long organizationId);

	int countByUnitIdAndBusinessIdAndOrganizationIdAndStatus(Long unitId, Long businessId, Long organizationId,
			Status active);

	@Query(value ="SELECT bi.Building_Name AS buildingName FROM unit_information c LEFT JOIN building_information AS bi ON bi.ID = c.Building_Information_Id WHERE c.ID = ?1",nativeQuery = true)
	Optional<String> getBuildingName(Long unitId);

	@Query(value ="SELECT fi.Floor_Name AS floorName FROM unit_information c LEFT JOIN floor_information AS fi ON fi.ID = c.FloorInformationId WHERE c.ID = ?1",nativeQuery = true)
	Optional<String> getFloorName(Long unitId);

}
