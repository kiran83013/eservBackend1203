package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

	@Query(value = "SELECT\r\n" + "br.ID,\r\n" + "br.BranchCategory,\r\n" + "mbc.Name AS BranchCategoryName, \r\n"
			+ "br.BranchType,\r\n" + "mbt.Name AS BranchTypeName, \r\n" + "br.Branch,\r\n"
			+ "br.BranchAddress AS branchAddress,\r\n" + "br.Code,\r\n" + "br.Code1,\r\n" + "br.Code2,\r\n"
			+ "br.Code3,\r\n" + "br.Code4,\r\n" + "br.Code5,\r\n" + "br.Country,\r\n" + "mc.Name AS CountryName,\r\n"
			+ "br.CreatedBy,\r\n" + "br.CreatedDate,\r\n" + "br.Currency,\r\n" + "mcur.Currency AS CurrencyName,\r\n"
			+ "br.EndDate,\r\n" + "br.ShortName,\r\n" + "br.StartDate,\r\n" + "br.Status,\r\n" + "br.UpdatedBy, \r\n"
			+ "br.UpdatedDate,\r\n" + "br.Bank AS BankId,\r\n" + "b.Name AS Bank,\r\n" + "br.OrganizationId,\r\n"
			+ "org.Name OrganizationName\r\n" + "FROM bank_branch br\r\n" + "LEFT JOIN bank b ON b.ID = br.Bank\r\n"
			+ "LEFT JOIN country_master mc ON mc.ID = br.Country\r\n"
			+ "LEFT JOIN master_branch_category mbc ON mbc.ID = br.BranchCategory\r\n"
			+ "LEFT JOIN master_branch_type mbt ON mbt.ID = br.BranchType\r\n"
			+ "LEFT JOIN country_master mcur ON mcur.ID = br.Currency\r\n"
			+ "LEFT JOIN master_organization org ON org.OrganizationId=br.OrganizationId\r\n" + "WHERE \r\n"
			+ "br.OrganizationId=?1\r\n" + "", nativeQuery = true)
	List<Map<String, String>> findAllList(Long orgId);

	Page<Branch> findByOrganizationId(Long orgId, Pageable page);

	@Query(value = "SELECT c.Name AS BranchCategoryName  FROM master_branch_category  c WHERE c.Id=?1", nativeQuery = true)
	Optional<String> getBranchCategoryName(Long id);
	
	@Query(value = "SELECT c.Name AS BranchTypeName  FROM master_branch_type  c WHERE c.Id=?1", nativeQuery = true)
	Optional<String> getBranchTypeName(Long brType);
	
	@Query(value = "SELECT c.Name AS CountryName  FROM country_master  c WHERE c.Id=?1",nativeQuery = true)
	Optional<String> getCountryName(Long id);
	
	@Query(value = "SELECT c.Name AS CurrencyName  FROM master_currency  c WHERE c.Id= ?1",nativeQuery = true)
	Optional<String> getCurrencyName(Long id);
//	List<Map<String, String>> findAllByBankNameList();
}
