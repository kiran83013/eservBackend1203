package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.Product;



public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByCustomerIdAndOrganizationId(Long customerId,Long organizationId);

	Page<Product> findByOrganizationId(Long organizationId, Pageable pageable);

//	@Query(value = "SELECT cp.*,\r\n" + 
//			"mpt.Name AS ProductType,\r\n" + 
//			"mpc.Name AS ProductCategory,\r\n" + 
//			"mpu.Name AS ProductUOM,\r\n" + 
//			"cb.Name AS ProductBrand\r\n" + 
//			"FROM customer_product cp\r\n" + 
//			"INNER JOIN masters_srm.master_product_type mpt ON mpt.ID = cp.Type \r\n" + 
//			"INNER JOIN masters_srm.master_product_category mpc ON mpc.ID = cp.Category \r\n" + 
//			"INNER JOIN masters_srm.master_product_uom mpu ON mpu.ID = cp.UOM \r\n" + 
//			"INNER JOIN srm.customer_brand cb ON cb.ID = cp.Brand \r\n" + 
//			"WHERE\r\n" + 
//			"cp.CustomerId = ?1", nativeQuery = true)
//	List<Map<String, String>> findCoustmerIdList(Long customerId);

//	@Query(value = "SELECT cp.*,\r\n" + 
//			"mpt.Name AS ProductType,\r\n" + 
//			"mpc.Name AS ProductCategory,\r\n" + 
//			"mpu.Name AS ProductUOM,\r\n" + 
//			"cnc.BusinessName,\r\n" + 
//			"cb.Name AS ProductBrand\r\n" + 
//			"FROM customer_product cp\r\n" + 
//			"INNER JOIN masters_srm.master_product_type mpt ON mpt.ID = cp.Type \r\n" + 
//			"INNER JOIN masters_srm.master_product_category mpc ON mpc.ID = cp.Category \r\n" + 
//			"INNER JOIN masters_srm.master_product_uom mpu ON mpu.ID = cp.UOM \r\n" + 
//			"INNER JOIN srm.customer_info cnc ON cnc.CUSTOMERID = cp.Business \r\n" + 
//			"INNER JOIN srm.customer_brand cb ON cb.ID = cp.Brand ", nativeQuery = true)
//	List<Map<String, String>> findAllLovs();

}