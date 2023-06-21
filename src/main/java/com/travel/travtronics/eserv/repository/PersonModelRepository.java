package com.travel.travtronics.eserv.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.eserv.model.PersonModel;

public interface PersonModelRepository extends JpaRepository<PersonModel, Long>, JpaSpecificationExecutor<PersonModel> {

	@Query("select person from PersonModel person where(person.firstName LIKE %?1% OR person.lastName LIKE %?1%  OR person.primaryPhoneNumber=?1 OR person.primaryEmail=?1)")
	List<PersonModel> findBySearchParameter(String search);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO master_designation(name,code,description,created_by,created_date) VALUES (?1,?2,?3,?4,?5)", nativeQuery = true)
	public void addDesignationInfo(String name, String code, String description, Integer createdBy, Date createdDate);

	Optional<PersonModel> findByPrimaryEmailAndPrimaryPhoneNumber(String primaryEmail, Long valueOf);

	@Query(value = "SELECT loc.LocationId AS locationId,loc.Name AS locationName,loc.Code AS locationCode,\r\n"
			+ "loc.CostCenterId AS ccId,cc.Name AS ccName,cc.Code AS ccCode,cc.BusinessUnitId AS buId,\r\n"
			+ "bu.Name AS buName,bu.Code AS buCode,bu.OrganizationId,org.Name AS orgName,org.Code AS orgCode\r\n"
			+ "FROM business_unit bu\r\n" + "INNER JOIN cost_center cc ON cc.BusinessUnitId=bu.BusinessUnitId\r\n"
			+ "INNER JOIN location loc ON loc.CostCenterId=cc.CostCenterId\r\n"
			+ "INNER JOIN master_organization org ON org.OrganizationId=bu.OrganizationId\r\n" + "WHERE\r\n"
			+ "bu.OrganizationId=?2 AND bu.BusinessUnitId=?1", nativeQuery = true)
	List<Map<String, Object>> getOrgBuLocCcInfo(Long buId, Long orgId);

	List<PersonModel> findAllByOrganizationId(Long orgId);

	Page<PersonModel> findByOrganizationId(Long orgId, Pageable page);

	Optional<PersonModel> findByPrimaryEmailOrPrimaryPhoneNumber(String primaryEmail, Long primaryPhoneNumber);

	@Query(value = "SELECT * FROM `person_profile` p \r\n" + "WHERE\r\n"
			+ "CONCAT(p.`FirstName`,'-',p.`LastName`,'-',p.`PrimaryPhoneNumber`) =?1 ORDER BY p.`ID` DESC LIMIT 1", nativeQuery = true)
	Optional<PersonModel> validatePerson(String uniqId);
}
