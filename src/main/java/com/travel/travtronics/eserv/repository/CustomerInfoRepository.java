package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.eserv.model.CustomerInfoModel;
import com.travel.travtronics.request.SupplierContactDto;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfoModel, Long> {
	List<CustomerInfoModel> findAll();

	// CustomerInfoModel findByCustomerCode(String customerCode);

	CustomerInfoModel findByCustomerId(Long customerId);

	CustomerInfoModel findByBusinessName(String businessName);

	List<CustomerInfoModel> findByBusinessNameContainsAndIsCustomerTrue(String businessName);

	List<CustomerInfoModel> findByBusinessNameContainsAndIsSupplierTrue(String businessName);

	List<CustomerInfoModel> findAllByIsSupplierTrue();

	@Query("select customer.businessName from CustomerInfoModel customer where customer.customerId=?1")
	Optional<String> findBusinessInfoByCustomerId(Long customerId);

	@Query(value = "SELECT ci.CUSTOMERID AS customerId,ci.BusinessName AS businessName,\r\n" + 
			"ci.ShortName AS shortName,ci.Type AS type,ci.Code AS code,ci.OrganizationId AS organizationId,\r\n" + 
			"ci.Category  AS category,ci.Industry AS industry,ci.SubIndustry AS subIndustry,mo.Name AS organizationName,\r\n" + 
			"ci.StartDate AS startDate,ci.EndDate AS endDate,ci.IsCustomer AS isCustomer,\r\n" + 
			"ci.Cust_RegistrationNumber AS custRegistrationNumber,ci.IsSupplier AS isSupplier,\r\n" + 
			"ci.Suppl_RegistrationNumber AS supplRegistrationNumber,ci.Rating AS rating,\r\n" + 
			"ci.Legacy_ID AS legacyID,ci.StatusWF AS wfStatus,ci.Status AS status,\r\n" + 
			"ci.CreatedBy AS createdBy,ci.CreatedDate AS createdDate,ci.UpdatedBy AS updatedBy,\r\n" + 
			"ci.UpdatedDate AS updatedDate,mt.Name AS typeName,mc.Name AS categoryName,\r\n" + 
			"mr.Name AS ratingName,mi.Name AS subIndustryName,mv.Name AS IndustryName,\r\n" + 
			"ci.SrId AS srId\r\n" + 
			"FROM customer_info ci LEFT JOIN master_business_type mt ON mt.ID=ci.Type\r\n" + 
			"LEFT JOIN master_category mc ON mc.category_id=ci.Category\r\n" + 
			"LEFT JOIN master_rating mr ON mr.ID=ci.Rating\r\n" + 
			"LEFT JOIN master_industry mi ON mi.ID=ci.SubIndustry\r\n" + 
			"LEFT JOIN master_organization mo ON mo.OrganizationId = ci.OrganizationId\r\n" + 
			"LEFT JOIN master_vertical mv ON mv.ID=ci.Industry AND ci.organizationId =?", nativeQuery = true)

	List<Map<String, String>> findAllCustomersByOrganizationId(Long organizationId);

	Page<CustomerInfoModel> findByOrganizationId(Long organizationId, Pageable pageable);
	
	@Query(value = "SELECT c.Name AS categoryName FROM master_business_category  c WHERE c.ID=?1",nativeQuery = true)
	Optional<String> getCategoryName(Integer category);

	@Query(value = "SELECT c.Name AS businessType FROM master_business_type  c WHERE c.ID=?1",nativeQuery = true)
	Optional<String> getTypeName(Integer type);

	@Query(value = "SELECT c.Name AS indIndustryName FROM master_industry  c WHERE c.ID=?1",nativeQuery = true)
	Optional<String> getIndustryName(Integer industry);

	

//	@Query(value = "SELECT c.Name AS srName FROM service_types_header  c WHERE c.ID=?1",nativeQuery = true)
//	Optional<String> getSrName(Long srId);

//
//	@Query(value = "SELECT CONCAT(contact.FirstName,'',contact.LastName) AS contact,contact.PrimaryEmail,\r\n" + 
//			"contact.PrimaryPhoneNumber,contact.ID AS contactId FROM customer_address address\r\n" + 
//			"INNER JOIN customer_contact contact ON address.PrimaryContactId=contact.ID\r\n" + 
//			"WHERE Primary_Address_Supplier=1 AND REFID=?1", nativeQuery = true)
//	SupplierContactDto getSupplierInfo(Long customerId);
	
	
	@Query(value = "SELECT CONCAT(p.FirstName,'',p.LastName) AS contact,p.PrimaryEmail,\r\n" + 
			"p.PrimaryPhoneNumber,p.ID AS contactId FROM customer_address address\r\n" + 
			"INNER JOIN person_profile p ON address.PrimaryContactId=p.ID\r\n" + 
			"WHERE Primary_Address_Supplier=1 AND REFID=?1", nativeQuery = true)
	SupplierContactDto getSupplierInfo(Long customerId);
	
}
