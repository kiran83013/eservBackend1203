package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.Bank;

//import com.travel.travtronics.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

//	List<Bank> findAll();

	@Query(value = "SELECT br.ID,\r\n" + "br.BankCategory,\r\n" + "mbc.Name AS BankCategoryName,\r\n"
			+ "br.BankType,\r\n" + "mbt.Name AS BankTypeName,\r\n" + "br.BIZ,ci.BusinessName,\r\n"
			+ "br.Code,br.Code1,br.Code2,br.Code3,br.Code4,br.Code5,\r\n"
			+ "br.Country,mc.Name AS CountryName,br.CreatedBy,br.CreatedDate,\r\n"
			+ "br.Currency,mcur.Currency AS CurrencyName,br.EndDate,br.Name,\r\n"
			+ "br.ShortName,br.StartDate,br.Status,br.UpdatedBy,br.UpdatedDate,\r\n"
			+ "org.Name AS OrganizationName,br.OrganizationId\r\n"
			+ "FROM  bank br LEFT JOIN country_master mc ON mc.ID = br.Country\r\n"
			+ "LEFT JOIN master_bank_category mbc ON mbc.ID = br.BankCategory\r\n"
			+ "LEFT JOIN master_bank_type mbt ON mbt.ID = br.BankType\r\n"
			+ "LEFT JOIN customer_info ci ON ci.CUSTOMERID = br.BIZ\r\n"
			+ "LEFT JOIN country_master mcur ON mcur.ID = br.Currency\r\n"
			+ "LEFT JOIN master_organization org ON org.OrganizationId=br.OrganizationId\r\n"
			+ "WHERE br.OrganizationId=?1", nativeQuery = true)
	List<Map<String, String>> findAllList(Long orgId);

	@Query(value = "SELECT sr.ID, sr.SR_Type_Id AS serviceTypeId,st.Name AS serviceTypeName, sr.Customer_Id AS customerId, ci.BusinessName AS customerName, sr.SR_Status_Id AS statusId, s.name AS statusName, sr.Submitted_Date AS submittedDate, t.TeamId AS teamId, t.TeamName AS teamName\r\n"
			+ "FROM e_service_request.service_request AS sr\r\n"
			+ "LEFT JOIN e_services.service_types_header AS st ON sr.SR_Type_Id = st.ID\r\n"
			+ "LEFT JOIN e_services.customer_info AS ci ON sr.Customer_Id = ci.CUSTOMERID\r\n"
			+ "LEFT JOIN e_services.status AS s ON sr.SR_Status_Id = s.StatusId\r\n"
			+ "LEFT JOIN e_services.team AS t ON sr.Team_ID = t.TeamId\r\n" + "WHERE sr.WiW=\"1\" ORDER BY ID\r\n"
			+ "LIMIT 10 OFFSET 0", nativeQuery = true)
	List<Map<String, String>> getList(Integer wwwId);

//	Page<Bank> findByOrganizationId(Long organizationId, Pageable pageable);

	@Query(value = "SELECT org.Name AS organizationName  FROM master_organization  org WHERE org.OrganizationId=?1", nativeQuery = true)
	Optional<String> getOrg(Long Id);

	@Query(value = "SELECT c.Name AS CountryName  FROM country_master  c WHERE c.Id=?1", nativeQuery = true)
	Optional<String> getCountryName(Long Id);

	@Query(value = "SELECT c.Name AS currencyName  FROM master_currency  c WHERE c.Id=?1", nativeQuery = true)
	Optional<String> getCurrencyName(Long Id);

	@Query(value = "SELECT mbt.Name AS bankTypeName FROM master_bank_type  mbt WHERE mbt.Id=?1", nativeQuery = true)
	Optional<String> getBankTypeName(Long Id);

	@Query(value = "SELECT mbt.Name AS bankCategoryName  FROM master_bank_category  mbt WHERE mbt.Id=?1", nativeQuery = true)
	Optional<String> getBankCtgyName(Long Id);

	@Query(value = "SELECT ci.BusinessName AS businessName FROM customer_info  ci WHERE ci.CUSTOMERID=1", nativeQuery = true)
	Optional<String> getBizName(Long Id);

//	Page<Bank> findByOrganizationId(Long organizationId, Pageable pageable);

	@Query("SELECT COUNT(*) FROM Bank b WHERE b.organizationId=?1")
	Long countByOrganizationId(Long organizationId);

	@Query("select count(*) from Bank  b")
	Long countAll();

	List<Bank> findByOrganizationId(Long organizationId);

}
