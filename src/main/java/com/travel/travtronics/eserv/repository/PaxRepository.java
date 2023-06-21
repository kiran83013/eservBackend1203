package com.travel.travtronics.eserv.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.eserv.model.Pax;

@Repository
public interface PaxRepository extends JpaRepository<Pax, Long> {

	Optional<Pax> findByPrimaryEmailAndPrimaryPhoneNumber(String primaryEmail, Long primaryPhoneNumber);

	@Query(value = "SELECT\r\n" + "pp.ID AS id,pp.CreatedBy AS createdBy,\r\n"
			+ "pp.CreatedDate AS createdDate,pp.CustomerId AS customerId,\r\n"
			+ "ci.BusinessName AS customerName,pp.DesignationId AS designationId,\r\n"
			+ "pp.DesignationName AS designationName,pp.EndDate AS endDate,\r\n"
			+ "pp.FirstName AS firstName,pp.LastName AS lastName,pp.MiddleName AS middleName,\r\n"
			+ "pp.Prefix AS prefix,mt.Name AS prefixName,pp.PrimaryCCP AS primaryCCP,\r\n"
			+ "pp.PrimaryEmail AS primaryEmail,pp.PrimaryPhoneNumber AS primaryPhoneNumber,\r\n"
			+ "pp.RemarksAndNotes AS remarksAndNotes,pp.RoleId AS roleId,mr.Name AS roleName,\r\n"
			+ "pp.SecondaryCCP AS secondaryCCP,pp.SecondaryEmail AS secondaryEmail,\r\n"
			+ "pp.SecondaryPhoneNumber AS secondaryPhoneNumber,pp.StartDate AS startDate,\r\n"
			+ "pp.Status AS status,pp.TelephoneNumber AS telephoneNumber,\r\n"
			+ "pp.UpdatedBy AS updatedBy,pp.UpdatedDate AS updatedDate,pp.DateOfBirth AS dob,\r\n"
			+ "pp.IssuedCountry AS issuedCountry,mc.Name AS issuedCountryName,\r\n"
			+ "pp.Nationality AS nationality,mn.Name AS nationalityName,pp.Passport AS passport\r\n"
			+ "FROM srm.pax_profile pp LEFT JOIN srm.customer_info ci ON ci.CUSTOMERID = pp.CustomerId\r\n"
			+ "LEFT JOIN masters_srm.master_role mr ON mr.ID = pp.RoleId\r\n"
			+ "LEFT JOIN masters_srm.master_country mc ON mc.ID = pp.IssuedCountry\r\n"
			+ "LEFT JOIN masters_srm.master_nationality mn ON mn.ID = pp.Nationality\r\n"
			+ "LEFT JOIN masters_srm.master_title mt ON mt.ID = pp.Prefix", nativeQuery = true)
	List<Map<String, String>> findAllByListLov();

	@Query(value = "SELECT\r\n" + "pp.ID AS id, \r\n" + "pp.CreatedBy AS createdBy,\r\n"
			+ "pp.CreatedDate AS createdDate,\r\n" + "pp.CustomerId AS customerId, \r\n"
			+ "ci.BusinessName AS customerName,\r\n" + "pp.DesignationId AS designationId, \r\n"
			+ "pp.DesignationName AS designationName, \r\n" + "pp.EndDate AS endDate,\r\n"
			+ "pp.FirstName AS firstName, \r\n" + "pp.LastName AS lastName,\r\n" + "pp.MiddleName AS middleName, \r\n"
			+ "pp.Prefix AS prefix,\r\n" + "mt.Name AS prefixName, \r\n" + "pp.PrimaryCCP AS primaryCCP, \r\n"
			+ "pp.PrimaryEmail AS primaryEmail,\r\n" + "pp.PrimaryPhoneNumber AS primaryPhoneNumber,\r\n"
			+ "pp.RemarksAndNotes AS remarksAndNotes, \r\n" + "pp.RoleId AS roleId,\r\n" + "mr.Name AS roleName,\r\n"
			+ "pp.SecondaryCCP AS secondaryCCP,\r\n" + "pp.SecondaryEmail AS secondaryEmail,\r\n"
			+ "pp.SecondaryPhoneNumber AS secondaryPhoneNumber,\r\n" + "pp.StartDate AS startDate,\r\n"
			+ "pp.Status AS status, \r\n" + "pp.TelephoneNumber AS telephoneNumber, \r\n"
			+ "pp.UpdatedBy AS updatedBy,\r\n" + "pp.UpdatedDate AS updatedDate, \r\n" + "pp.DateOfBirth AS dob,\r\n"
			+ "pp.IssuedCountry AS issuedCountry,\r\n" + "mc.Name AS issuedCountryName,\r\n"
			+ "pp.Nationality AS nationality, \r\n" + "mn.Name AS nationalityName,\r\n"
			+ "pp.ImageUrl AS dpImageUrl,\r\n" + "pp.Passport AS passport\r\n" + "FROM srm.pax_profile pp \r\n"
			+ "LEFT JOIN srm.customer_info ci ON ci.CUSTOMERID = pp.CustomerId\r\n"
			+ "LEFT JOIN masters_srm.master_role mr ON mr.ID = pp.RoleId\r\n"
			+ "LEFT JOIN masters_srm.master_country mc ON mc.ID = pp.IssuedCountry\r\n"
			+ "LEFT JOIN masters_srm.master_country mn ON mn.ID = pp.Nationality\r\n"
			+ "LEFT JOIN masters_srm.master_title mt ON mt.ID = pp.Prefix\r\n" + "WHERE\r\n"
			+ "pp.ID = ?1", nativeQuery = true)
	Optional<Map<String, Object>> findPaxIdByFind(Long id);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO master_designation(name,code,description,created_by,created_date) VALUES (?1,?2,?3,?4,?5)", nativeQuery = true)
	public void designationNewValues(String name, String code, String description, Integer createdBy, Date createdDate);

	@Query(value = "SELECT Name FROM masters_srm.master_nationality WHERE ID=?1", nativeQuery = true)
	String findByNationalityId(Long id);

	@Query(value = "SELECT Name FROM masters_srm.master_country WHERE ID=?1", nativeQuery = true)
	String findByCountryId(Long id);

}
