package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.enums.Status;
import com.travel.travtronics.eserv.model.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Long>{

	@Query(value = "SELECT\r\n" + 
			"ca.ADDRESSID AS addressId, \r\n" + 
			"ca.AddressDescription AS addressDescription,\r\n" + 
			"ca.AddressName AS addressName,\r\n" + 
			"ca.Area AS AREA,\r\n" + 
			"ca.BillingAndStreet AS billingAndStreet,\r\n" + 
			"ca.City AS city,\r\n" + 
			"ca.Country AS country, \r\n" + 
			"ca.CreatedBy AS createdBy, \r\n" + 
			"ca.CreatedDate AS createdDate,\r\n" + 
			"ca.Customer_AddressType AS customerAddressType,\r\n" + 
			"ca.Email AS email,\r\n" + 
			"ca.GeoLocation AS geoLocation,\r\n" + 
			"ca.LandMark AS landMark,\r\n" + 
			"ca.Phone1 AS phone1,\r\n" + 
			"ca.Phone2 AS phone2,\r\n" + 
			"ca.Primary_Address_Customer AS primaryAddressCustomer,\r\n" + 
			"ca.Primary_Address_Supplier AS primaryAddressSupplier, \r\n" + 
			"ca.PrimaryContactId AS primaryContactId,\r\n" + 
			"CONCAT(e.FirstName,' ',e.LastName) AS primaryContactName,\r\n" + 
			"ca.Primary_Supplier AS primarySupplier,\r\n" + 
			"ca.REFID AS refId,\r\n" + 
			"ca.Reg_Address AS regAddress, \r\n" + 
			"ca.SEmail AS sEmail,  \r\n" + 
			"ca.State AS state,\r\n" + 
			"ca.Status AS status,\r\n" + 
			"ca.OrganizationId AS organizationId,\r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"ca.Supplier_AddressType AS supplierAddressType,\r\n" + 
			"ca.UpdatedBy AS updatedBy,\r\n" + 
			"ca.UpdatedDate AS updatedDate,\r\n" + 
			"ca.WfStatus AS wfStatus,\r\n" + 
			"ca.ZipCode AS zipCode\r\n" + 
			"FROM customer_address ca\r\n" + 
			"LEFT JOIN person_profile e ON e.ID = ca.PrimaryContactId\r\n" + 
			"LEFT JOIN master_organization AS mo ON mo.OrganizationId = ca.OrganizationId\r\n" + 
			"WHERE \r\n" + 
			"ca.REFID = ?1 AND ca.OrganizationId = ?2\r\n" + 
			"AND ca.Status = 'Active'", nativeQuery = true)
	List<Map<String,String>> findAllByRefIdAndStatus(Long refId, Long organizationId,Status active);

	Page<CustomerAddress> findByOrganizationId(Long organizationId, Pageable pageable);

}
