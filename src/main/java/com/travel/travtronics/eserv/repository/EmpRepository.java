package com.travel.travtronics.eserv.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.eserv.model.Employee;
import com.travel.travtronics.response.EmpListDto;
import com.travel.travtronics.util.QueryConst;

public interface EmpRepository extends JpaRepository<Employee, Long> {

	@Query(value = QueryConst.GET_EMP_LIST, nativeQuery = true)
	List<EmpListDto> findAllList(Long organizationId);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO e_services.master_designation(Name,Code,Description,CreatedBy,CreatedDate,Status) VALUES (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
	void designationNewValues(String name, String code, String description, Integer createdBy, Date createdDate,
			Boolean status);

	// @Query(value = "SELECT * FROM emp_profile e WHERE (e.FirstName LIKE %?1% OR
	// e.LastName LIKE %?1% OR e.PrimaryEmail=?1 OR e.PrimaryPhoneNumber=?1)",
	// nativeQuery = true)

	@Query(value = "SELECT * FROM emp_profile  e WHERE (e.FirstName LIKE %?1% OR e.LastName LIKE %?1% OR e.PrimaryEmail LIKE %?1%  OR e.PrimaryPhoneNumber LIKE %?1%)", nativeQuery = true)
	List<EmpListDto> findBySearchParameter(String search);

	@Query(value = "SELECT * FROM emp_profile  e WHERE (e.FirstName LIKE %?1% OR e.LastName LIKE %?2% OR e.PrimaryEmail LIKE %?3%  OR e.PrimaryPhoneNumber LIKE %?4%)", nativeQuery = true)
	List<Map<String, String>> findByFirstNameLikeOrLastNameLikeOrPrimaryEmailLikeOrPrimaryPhoneNumberLike(String firstName, String lastName,
			String primaryEmail, Long primaryPhoneNumber);
	
	@Query(value = "SELECT * FROM emp_profile  e WHERE (e.CustomersId LIKE %?1% OR e.FirstName LIKE %?2% OR e.LastName LIKE %?3% OR e.PrimaryEmail LIKE %?4%  OR e.PrimaryPhoneNumber LIKE %?5%)", nativeQuery = true)
	List<Map<String, String>> findByCoustmerIdSearch(Long customersId, String firstName, String lastName,
			String primaryEmail, Long primaryPhoneNumber);
//==========================
//wapper class querrys
//==========================
	Optional<Employee> findByPrimaryEmailAndPrimaryPhoneNumber(String primaryEmail, Long valueOf);
	
//Filters Querrys
	@Query(value = "SELECT dept.Name  FROM master_departments  dept WHERE dept.DepartmentId=?1",nativeQuery = true)
	Optional<String> getDept(Long Id);
	
	@Query(value = "SELECT bu.Name  FROM business_unit  bu WHERE bu.BusinessUnitId=?1", nativeQuery = true)
	Optional<String> getBu(Long Id);
	
	@Query(value = "SELECT org.Name  FROM master_organization  org WHERE org.OrganizationId=?1",nativeQuery = true)
	Optional<String> getOrg(Long Id);
	
	@Query(value = "SELECT cm.Name  FROM country_master cm WHERE cm.Id=?1",nativeQuery = true)
	Optional<String> getCountryName(Long Id);

	Page<Employee> findByOrganizationId(Long organizationId, Pageable pageable);

}
