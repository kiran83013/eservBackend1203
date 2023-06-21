package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.eserv.model.Brand;


public interface BrandRepository extends JpaRepository<Brand, Long>{


//	@Query(value = "SELECT cb.*,\r\n" + 
//			"mbt.Name AS BrandType,\r\n" + 
//			"mbc.Name AS BrandCategory\r\n" + 
//			"FROM customer_brand cb\r\n" + 
//			"INNER JOIN masters_srm.master_brand_type mbt ON mbt.ID = cb.Type \r\n" + 
//			"INNER JOIN masters_srm.master_brand_category mbc ON mbc.ID = cb.Category \r\n" + 
//			"WHERE\r\n" + 
//			"CustomerId = ?1\r\n" + 
//			"", nativeQuery = true)
//	List<Map<String, String>> findByCustomerIdList(Long customerId);

	List<Brand> findByCustomerId(Long customerId);

	List<Brand> findAllByOrganizationId(Long organizationId);

	Page<Brand> findByOrganizationId(Long organizationId, Pageable pageable);

}
