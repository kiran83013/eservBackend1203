package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.CustomerBank;

public interface CustomerBankRepository extends JpaRepository<CustomerBank, Long>{
	
	@Query(value = "SELECT e.ID AS id,\r\n" + 
			"e.RefId AS refId,\r\n" + 
			"e.BankId AS bankId,\r\n" + 
			"e.BankName AS bankName,\r\n" + 
			"e.BranchId AS branchId,\r\n" + 
			"e.BranchName AS branchName,\r\n" + 
			"e.BranchAddress AS branchAddress,\r\n" + 
			"e.BankAccountType AS bankAcountType,\r\n" + 
			"mt.Name AS bankAcountTypeName,\r\n" + 
			"e.BankAccountName AS bankAccountName,\r\n" + 
			"e.BankAccountNumber AS bankAccountNumber,\r\n" + 
			"e.ChequeDigits AS chequeDigits,\r\n" + 
			"e.Currency AS currency, \r\n" + 
			"mc.Name AS currencyName, \r\n" + 
			"e.SwiftCode AS swiftCode,\r\n" + 
			"e.OrganizationId AS organizationId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"e.Notes AS notes,\r\n" + 
			"e.FileUrl AS fileUrl,\r\n" + 
			"e.CreatedBy AS createdBy,\r\n" + 
			"e.CreatedDate AS createdDate, \r\n" + 
			"e.UpdatedBy AS updatedBy,\r\n" + 
			"e.UpdatedDate AS updatedDate,\r\n" + 
			"e.Status AS STATUS\r\n" + 
			"FROM customer_bank e\r\n" + 
			"LEFT JOIN country_master mc ON mc.ID=e.Currency \r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = e.OrganizationId\r\n" + 
			"LEFT JOIN master_bank_type mt ON mt.ID=e.BankAccountType",nativeQuery = true)
	List<Map<String, String>> findAllList();

	List<CustomerBank> findByRefIdAndOrganizationId(Long refId, Long organizationId);

	@Query(value = "SELECT e.ID AS id,\r\n" + 
			"e.RefId AS refId,\r\n" + 
			"ci.BusinessName AS customerName,\r\n" + 
			"e.BankId AS bankId,\r\n" + 
			"e.BankName AS bankName,\r\n" + 
			"e.BranchId AS branchId,\r\n" + 
			"e.BranchName AS branchName,\r\n" + 
			"e.BranchAddress AS branchAddress,\r\n" + 
			"e.BankAccountType AS bankAcountType,\r\n" + 
			"mt.Name AS bankAcountTypeName,\r\n" + 
			"e.BankAccountName AS bankAccountName,\r\n" + 
			"e.BankAccountNumber AS bankAccountNumber,\r\n" + 
			"e.ChequeDigits AS chequeDigits,\r\n" + 
			"e.Currency AS currency,\r\n" + 
			"mc.Name AS currencyName,\r\n" + 
			"e.SwiftCode AS swiftCode,\r\n" + 
			"e.OrganizationId AS organizationId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"e.Notes AS notes,\r\n" + 
			"e.FileUrl AS fileUrl,\r\n" + 
			"e.CreatedBy AS createdBy,\r\n" + 
			"e.CreatedDate AS createdDate,\r\n" + 
			"e.UpdatedBy AS updatedBy,\r\n" + 
			"e.UpdatedDate AS updatedDate,\r\n" + 
			"e.Status AS status\r\n" + 
			"FROM customer_bank e\r\n" + 
			"LEFT JOIN country_master mc ON mc.ID=e.Currency\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = e.OrganizationId\r\n" + 
			"LEFT JOIN master_bank_type AS mt ON mt.ID=e.BankAccountType\r\n" + 
			"LEFT JOIN customer_info AS ci ON ci.CUSTOMERID = e.RefId\r\n" + 
			"WHERE e.OrganizationId = ?1\r\n" + 
			"AND e.RefId = ?2", countQuery = "SELECT COUNT(*) FROM customer_bank e WHERE e.OrganizationId = ?1 AND e.RefId = ?2", nativeQuery = true)
	Page<Map<String, String>> findByOrganizationIdAndRefId(Long organizationId, Long refId, Pageable pageable);

	@Query(value = "SELECT e.ID AS id,\r\n" + 
			"e.RefId AS refId,\r\n" + 
			"ci.BusinessName AS customerName,\r\n" + 
			"e.BankId AS bankId,\r\n" + 
			"e.BankName AS bankName,\r\n" + 
			"e.BranchId AS branchId,\r\n" + 
			"e.BranchName AS branchName,\r\n" + 
			"e.BranchAddress AS branchAddress,\r\n" + 
			"e.BankAccountType AS bankAcountType,\r\n" + 
			"mt.Name AS bankAcountTypeName,\r\n" + 
			"e.BankAccountName AS bankAccountName,\r\n" + 
			"e.BankAccountNumber AS bankAccountNumber,\r\n" + 
			"e.ChequeDigits AS chequeDigits,\r\n" + 
			"e.Currency AS currency, \r\n" + 
			"mc.Name AS currencyName, \r\n" + 
			"e.SwiftCode AS swiftCode,\r\n" + 
			"e.OrganizationId AS organizationId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"e.Notes AS notes,\r\n" + 
			"e.FileUrl AS fileUrl,\r\n" + 
			"e.CreatedBy AS createdBy,\r\n" + 
			"e.CreatedDate AS createdDate, \r\n" + 
			"e.UpdatedBy AS updatedBy,\r\n" + 
			"e.UpdatedDate AS updatedDate,\r\n" + 
			"e.Status AS status\r\n" + 
			"FROM customer_bank e\r\n" + 
			"LEFT JOIN country_master mc ON mc.ID=e.Currency \r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = e.OrganizationId\r\n" + 
			"LEFT JOIN customer_info AS ci ON ci.CUSTOMERID = e.RefId\r\n" +
			"LEFT JOIN master_bank_type mt ON mt.ID=e.BankAccountType", countQuery = "SELECT COUNT(*) FROM customer_bank", nativeQuery = true)
	Page<Map<String, String>> findAllList(Pageable pageable);

	

}
