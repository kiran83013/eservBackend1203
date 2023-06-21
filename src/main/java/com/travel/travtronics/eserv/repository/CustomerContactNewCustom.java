package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.Employee;
import com.travel.travtronics.request.EmpRequest;

import com.travel.travtronics.request.SearchResponseDto;

public interface CustomerContactNewCustom {

	List<SearchResponseDto> findAllBySearch(String firstName, String lastName, String primaryEmail,
			Long primaryPhoneNumber, Long customersId);

	public List<Employee> findContactBySearchParameters(String name, Long customerId, String primaryEmail,
			Long primaryPhoneNumber, String firstName, String lastName);

//	@Query(value = "SELECT e.ID AS id,\r\n" + 
//			"e.BusinessUnitId AS businessUnitId,\r\n" + 
//			"bu.Name AS businessUnitName,\r\n" + 
//			"e.DepartmentId AS departmentId,\r\n" + 
//			"md.Name AS departmentName,\r\n" + 
//			"e.CreatedBy AS createdBy,\r\n" + 
//			"e.CreatedDate AS createdDate,\r\n" + 
//			"e.DesignationId AS designationId,\r\n" + 
//			"e.DesignationName AS designationName,\r\n" + 
//			"e.DateOfBirth AS dateOfBirth,\r\n" + 
//			"e.ImageUrl AS imageUrl,\r\n" + 
//			"e.EndDate AS endDate,\r\n" + 
//			"e.FirstName AS firstName,\r\n" + 
//			"e.IssuedCountry AS issuedCountry,\r\n" + 
//			"mc.Name AS issuedCountryName,\r\n" + 
//			"e.MiddleName AS middleName,\r\n" + 
//			"e.LastName AS lastName,\r\n" + 
//			"e.Nationality AS nationality,\r\n" + 
//			"e.OrganizationId AS organizationId,\r\n" + 
//			"mo.Name AS organizationName,\r\n" + 
//			"e.Passport AS passport,\r\n" + 
//			"e.Prefix AS prefix,\r\n" + 
//			"e.PrimaryCCP AS primaryCCP,\r\n" + 
//			"e.PrimaryEmail AS primaryEmail,\r\n" + 
//			"e.PrimaryPhoneNumber AS primaryPhoneNumber,\r\n" + 
//			"e.RemarksAndNotes AS remarksAndNotes,\r\n" + 
//			"e.SecondaryCCP AS secondaryCCP,\r\n" + 
//			"e.SecondaryEmail AS secondaryEmail,\r\n" + 
//			"e.SecondaryPhoneNumber AS secondaryPhoneNumber,\r\n" + 
//			"e.StartDate AS startDate,\r\n" + 
//			"e.Status AS STATUS,\r\n" + 
//			"e.TelephoneNumber AS telephoneNumber,\r\n" + 
//			"e.UpdatedBy AS updatedBy,\r\n" + 
//			"e.UpdatedDate AS updatedDate,\r\n" + 
//			"e.CustomersId AS customersId,\r\n" + 
//			"ci.BusinessName AS businessName\r\n" + 
//			"#con.Id AS contactId,\r\n" + 
//			"#con.status AS contactStatus\r\n" + 
//			"FROM emp_profile e\r\n" + 
//			"LEFT JOIN country_master mc ON e.IssuedCountry=mc.ID\r\n" + 
//			"LEFT JOIN customer_contact con ON e.ID=con.PaxID\r\n" + 
//			"LEFT JOIN customer_info ci ON ci.CUSTOMERID = e.CustomersId\r\n" + 
//			"LEFT JOIN business_unit bu ON bu.BusinessUnitId = e.BusinessUnitId\r\n" + 
//			"LEFT JOIN master_departments md ON md.DepartmentId = e.DepartmentId\r\n" + 
//			"LEFT JOIN master_organization mo ON mo.OrganizationId = e.OrganizationId\r\n" + 
//			"WHERE\r\n" + 
//			"(e.CustomersId LIKE %?1% OR e.FirstName LIKE %?2% OR e.LastName LIKE %?3% OR e.PrimaryEmail LIKE %?4%  OR e.PrimaryPhoneNumber LIKE %?5%)\r\n" + 
//			"", nativeQuery = true)

//	@Query(value = "SELECT * FROM emp_profile  e WHERE (e.CustomersId LIKE %?1% OR e.FirstName LIKE %?2% OR e.LastName LIKE %?3% OR e.PrimaryEmail LIKE %?4%  OR e.PrimaryPhoneNumber LIKE %?5%)", nativeQuery = true)
//	List<Map<String, String>> findAllBySearchList(String firstName, String lastName, String primaryEmail,
//			Long primaryPhoneNumber, Long customerId);

}
