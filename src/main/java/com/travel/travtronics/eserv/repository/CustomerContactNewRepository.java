package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.response.CustomerSearchDto;

public interface CustomerContactNewRepository extends JpaRepository<CustomerContactNew, Long> {

//	@Query(value = "SELECT cnc.ID AS id, \r\n" + "cnc.CreatedBy AS createdBy, \r\n"
//			+ "cnc.CreatedDate AS createdDate, \r\n" + "cnc.DesignationId AS designationId,\r\n"
//			+ "cnc.DesignationName AS designationName,\r\n" + "cnc.EndDate AS endDate,\r\n"
//			+ "cnc.FirstName AS firstName,\r\n" + "cnc.LastName AS lastName, \r\n"
//			+ "cnc.MiddleName AS middleName, \r\n" + "cnc.PaxId AS paxId,\r\n" + "pf.FirstName AS paxName, \r\n"
//			+ "cnc.PrefixID AS prefix, \r\n" + "mt.Name AS prefixName,\r\n" + "cnc.PrimaryCCP AS primaryCCP, \r\n"
//			+ "cnc.PrimaryEmail AS primaryEmail,\r\n" + "cnc.PrimaryPhoneNumber AS primaryPhoneNumber,\r\n"
//			+ "cnc.RemarksAndNotes AS remarksAndNotes, \r\n" + "cnc.RoleId AS roleId,\r\n" + "mr.Name AS roleName, \r\n"
//			+ "cnc.SecondaryCCP AS secondaryCCP,\r\n" + "cnc.SecondaryEmail AS secondaryEmail,\r\n"
//			+ "cnc.SecondaryPhoneNumber AS secondaryPhoneNumber,\r\n" + "cnc.StartDate AS startDate,\r\n"
//			+ "cnc.Status AS status,\r\n" + "cnc.TelephoneNumber AS telephoneNumber,\r\n"
//			+ "cnc.UpdatedBy AS updatedBy,\r\n" + "cnc.UpdatedDate AS updatedDate,\r\n" + "cnc.DateOfBirth AS dob,\r\n"
//			+ "cnc.IssuedCountry AS issuedCountry,\r\n" + "mc.Name AS issuedCountryName,\r\n"
//			+ "cnc.Nationality AS nationality, \r\n" + "nc.Name AS nationalityName,\r\n"
//			+ "cnc.Passport AS passport,\r\n" + "cnc.CustomerId AS customerId,\r\n"
//			+ "ci.BusinessName AS customerName,\r\n" + "cnc.ImageUrl AS dpImageUrl\r\n"
//			+ "FROM customer_contact cnc\r\n" + "LEFT JOIN customer_info ci ON ci.CUSTOMERID = cnc.`CustomerId`\r\n"
//			+ "LEFT JOIN pax_profile pf ON pf.ID = cnc.PaxId\r\n"
//			+ "LEFT JOIN masters_srm.master_role mr ON mr.ID = cnc.RoleId \r\n"
//			+ "LEFT JOIN masters_srm.master_country mc ON mc.ID = cnc.IssuedCountry\r\n"
//			+ "LEFT JOIN masters_srm.master_nationality nc ON nc.ID = cnc.Nationality\r\n"
//			+ "LEFT JOIN masters_srm.master_title mt ON mt.ID = cnc.PrefixID\r\n" + "WHERE\r\n"
//			+ "cnc.ID = ?1", nativeQuery = true)
//	List<Map<String, String>> findListOfColums(Long id);

	@Query(value = "SELECT cnc.ID AS id,cnc.CreatedBy AS createdBy,\r\n"
			+ "cnc.CreatedDate AS createdDate,cnc.CustomerId AS customerId,\r\n"
			+ "ci.BusinessName AS customerName,cnc.DesignationId AS designationId,\r\n"
			+ "cnc.DesignationName AS designationName,cnc.EndDate AS endDate,\r\n"
			+ "cnc.FirstName AS firstName,cnc.LastName AS lastName,cnc.MiddleName AS middleName,cnc.PrefixID AS prefix,\r\n"
			+ "mt.Name AS prefixName,cnc.PrimaryCCP AS primaryCCP,\r\n"
			+ "cnc.PrimaryEmail AS primaryEmail,cnc.PrimaryPhoneNumber AS primaryPhoneNumber,\r\n"
			+ "cnc.RemarksAndNotes AS remarksAndNotes,cnc.RoleId AS roleId,mr.Name AS roleName,\r\n"
			+ "cnc.SecondaryCCP AS secondaryCCP,cnc.SecondaryEmail AS secondaryEmail,\r\n"
			+ "cnc.SecondaryPhoneNumber AS secondaryPhoneNumber,cnc.StartDate AS startDate,\r\n"
			+ "cnc.Status AS status,cnc.TelephoneNumber AS telephoneNumber,cnc.OrganizationId AS organizationId,\r\n"
			+ "mo.Name AS organizationName,\r\n"
			+ "cnc.UpdatedBy AS updatedBy,cnc.UpdatedDate AS updatedDate,cnc.DateOfBirth AS dob,\r\n"
			+ "cnc.IssuedCountry AS issuedCountry,mc.Name AS issuedCountryName,\r\n"
			+ "cnc.Nationality AS nationality,nc.Name AS nationalityName,cnc.Passport AS passport\r\n"
			+ "FROM customer_contact cnc \r\n" + "LEFT JOIN customer_info ci ON ci.CUSTOMERID = cnc.ID\r\n"
			+ "LEFT JOIN master_role mr ON mr.ID = cnc.RoleId\r\n"
			+ "LEFT JOIN country_master mc ON mc.ID = cnc.IssuedCountry\r\n"
			+ "LEFT JOIN master_organization AS mo ON mo.OrganizationId = cnc.OrganizationId\r\n"
			+ "LEFT JOIN country_master nc ON nc.ID = cnc.Nationality\r\n"
			+ "LEFT JOIN master_prefix mt ON mt.ID = cnc.PrefixID WHERE\r\n"
			+ "cnc.CustomerId = ?1 AND cnc.OrganizationId = ?2", nativeQuery = true)
	List<Map<String, String>> findByCustomerIdAndOrganizationId(Long customerId, Long organizationId);

	@Query(value = "SELECT cnc.ID AS id,cnc.CreatedBy AS createdBy,\r\n"
			+ "cnc.CreatedDate AS createdDate,cnc.CustomerId AS customerId,\r\n"
			+ "ci.BusinessName AS customerName,cnc.DesignationId AS designationId,\r\n"
			+ "cnc.DesignationName AS designationName,cnc.EndDate AS endDate,\r\n"
			+ "cnc.FirstName AS firstName,cnc.LastName AS lastName,cnc.MiddleName AS middleName,\r\n"
			+ "cnc.PrefixID AS prefix,mt.Name AS prefixName,cnc.PrimaryCCP AS primaryCCP,\r\n"
			+ "cnc.PrimaryEmail AS primaryEmail,cnc.PrimaryPhoneNumber AS primaryPhoneNumber,\r\n"
			+ "cnc.RemarksAndNotes AS remarksAndNotes,cnc.RoleId AS roleId,mr.Name AS roleName,\r\n"
			+ "cnc.SecondaryCCP AS secondaryCCP,cnc.SecondaryEmail AS secondaryEmail,\r\n"
			+ "cnc.SecondaryPhoneNumber AS secondaryPhoneNumber,cnc.StartDate AS startDate,cnc.OrganizationId AS organizationId,\r\n"
			+ "mo.Name AS organizationName,\r\n" + "cnc.Status AS status,cnc.TelephoneNumber AS telephoneNumber,\r\n"
			+ "cnc.UpdatedBy AS updatedBy,cnc.UpdatedDate AS updatedDate,cnc.DateOfBirth AS dob,\r\n"
			+ "cnc.IssuedCountry AS issuedCountry,mc.Name AS issuedCountryName,\r\n"
			+ "cnc.Nationality AS nationality,nc.Name AS nationalityName,cnc.Passport AS passport\r\n"
			+ "FROM customer_contact cnc\r\n" + "LEFT JOIN customer_info ci ON ci.CUSTOMERID = cnc.ID\r\n"
			+ "LEFT JOIN master_organization AS mo ON mo.OrganizationId = cnc.OrganizationId\r\n"
			+ "LEFT JOIN master_role mr ON mr.ID = cnc.RoleId\r\n"
			+ "LEFT JOIN country_master mc ON mc.ID = cnc.IssuedCountry\r\n"
			+ "LEFT JOIN country_master nc ON nc.ID = cnc.Nationality\r\n"
			+ "LEFT JOIN master_prefix mt ON mt.ID = cnc.PrefixID WHERE cnc.organizationId = ?1 AND cnc.customerId = ?2", nativeQuery = true)
	List<Map<String, String>> findAllList(Long organizationId, Long customerId);

	Optional<CustomerContactNew> findByPrimaryEmailAndPrimaryPhoneNumber(String primaryEmail, Long primaryPhoneNumber);

	@Query("select contact from CustomerContactNew contact where (contact.firstName like %?1% or contact.lastName like %?1% or concat(contact.firstName,' ',contact.lastName) like %?1%) and contact.customerId=?2")
	List<CustomerContactNew> findBySearchParamAndCustomerId(String searchParam, Long customerId);

	List<CustomerContactNew> findAllById(Long id);

	@Query(value = "SELECT pp.BusinessUnitId AS buId,bu.Name AS buName,coc.CostCenterId AS ccId, coc.Name ccName,\r\n"
			+ "loc.LocationId  AS locId,loc.Name AS locName,pp.OrganizationId AS orgId,org.Name AS orgName \r\n"
			+ "FROM customer_contact cc\r\n" + "INNER JOIN person_profile pp ON pp.ID=cc.PersonId\r\n"
			+ "LEFT JOIN  business_unit bu ON bu.BusinessUnitId=pp.BusinessUnitId\r\n"
			+ "LEFT JOIN cost_center coc ON coc.BusinessUnitId=bu.BusinessUnitId\r\n"
			+ "LEFT JOIN location loc ON loc.CostCenterId=coc.CostCenterId\r\n"
			+ "LEFT JOIN master_organization org ON org.OrganizationId=pp.OrganizationId\r\n"
			+ "WHERE pp.ID=?1", nativeQuery = true)
	CustomerSearchDto getBuCCOrdLocInfo(Long personId);

	List<CustomerContactNew> findAll(Specification<CustomerContactNew> findByContactSpecifications);

	Page<CustomerContactNew> findByOrganizationId(Long organizationId, Pageable pageable);

	Optional<CustomerContactNew> findByPrimaryEmailOrPrimaryPhoneNumber(String primaryEmail, Long primaryPhoneNumber);

	@Query(value = "SELECT * FROM `customer_contact` p \r\n" + "WHERE\r\n"
			+ "CONCAT(p.`FirstName`,'-',p.`LastName`,'-',p.`PrimaryPhoneNumber`) =?1 ORDER BY p.`ID` DESC LIMIT 1", nativeQuery = true)
	Optional<CustomerContactNew> validateContact(String uniqId);

	Optional<CustomerContactNew> findByPrimaryPhoneNumber(Long primaryPhoneNumber);

//	List<CustomerContactNew> findByCustomerId(Long customerId);

//	Optional<CustomerContactNew> findByIdAndCustomerId(Long id, Long customerId);

}
