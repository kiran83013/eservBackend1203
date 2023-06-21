package com.travel.travtronics.eserv.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.travel.travtronics.request.SearchBusinessDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.CustomerSearchInfoResponse;

@Component
public class CustomerInfoServiceImpl implements CustomerSearchRepository {

	@PersistenceContext
	@Autowired
	EntityManager entityManager;

	@Override
	public List<CustomerSearchInfoResponse> searchUnitInfo(SearchBusinessDto searchDto) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT business_info_report.*FROM   (SELECT ci.customerid AS customerId,\r\n" + 
				"  ci.businessname AS businessName,\r\n" + 
				" ci.shortname   AS shortName,\r\n" + 
				" ci.type        AS 'type',   \r\n" + 
				" mt.name    AS typeName,\r\n" + 
				" ci.code  AS 'code',          \r\n" + 
				" ci.category AS category, \r\n" + 
				" mc.name AS categoryName, \r\n" + 
				"  ci.industry AS industry,          \r\n" + 
				"  mv.name AS IndustryName,\r\n" + 
				"  ci.subindustry        AS subIndustry,\r\n" + 
				"  mi.name   AS subIndustryName,\r\n" + 
				"  ci.startdate  AS startDate,\r\n" + 
				"    ci.enddate  AS endDate,\r\n" + 
				"    ci.iscustomer  AS isCustomer,\r\n" + 
				"   ci.cust_registrationnumber AS custRegistrationNumber,\r\n" + 
				"   ci.issupplier AS isSupplier, \r\n" + 
				"   ci.suppl_registrationnumber AS supplRegistrationNumber,   \r\n" + 
				"   ci.rating AS rating,   \r\n" + 
				"   mr.name  AS ratingName, \r\n" + 
				"   ci.legacy_id      AS legacyID,\r\n" + 
				"   ci.statuswf  AS wfStatus, ci.status AS 'status', \r\n" + 
				"   ci.createddate AS createdDate, ci.updateddate AS updatedDate,\r\n" + 
				"  ci.createdby  AS createdBy, ci.updatedby   AS updatedBy, c.businessunit AS businessUnitId,\r\n" + 
				"  c.organization AS organizationId, c.location AS locationId, c.costcenter AS costCenterId, \r\n" + 
				"  ( CASE WHEN c.businessunit IS NOT NULL THEN\r\n" + 
				"(SELECT `Name` FROM   e_services.business_unit WHERE  BusinessUnitId = c.businessunit) ELSE '' END )AS businessUnitName,\r\n" + 
				"( CASE WHEN c.organization IS NOT NULL THEN\r\n" + 
				"(SELECT `Name` FROM   e_services.master_organization  WHERE  `organizationid` = c.organization) ELSE '' END )AS organizationName,\r\n" + 
				"( IF(c.location IS NOT NULL, (SELECT `Name` FROM   e_services.location WHERE  `LocationId` = c.location), '') ) AS locationName,\r\n" + 
				" ( IF(c.costcenter IS NOT NULL, (SELECT `Name`   FROM e_services.cost_center  WHERE `costcenterid` = c.costcenter),'') ) AS costCenterName\r\n" + 
				" FROM   e_services.customer_info ci\r\n" + 
				"LEFT JOIN e_services.master_business_type mt ON mt.ID = ci.type\r\n" + 
				"LEFT JOIN e_services.master_business_category mc ON mc.ID = ci.category\r\n" + 
				"LEFT JOIN e_services.master_rating mr ON mr.ID = ci.rating\r\n" + 
				"LEFT JOIN e_services.master_vertical mv ON mv.ID = ci.industry\r\n" + 
				"LEFT JOIN e_services.master_industry mi ON mi.ID = ci.subindustry\r\n" + 
				"LEFT JOIN e_services.customer_creditterms c ON c.customerid = ci.customerid) AS business_info_report WHERE  1 = 1"
				+ System.lineSeparator());

		if (searchDto.getCustomerId() != null && searchDto.getCustomerId() != 0) {
			sql.append("AND business_info_report.customerid= :customerId" + System.lineSeparator());
			params.put("customerId", searchDto.getCustomerId());
		}

		if (searchDto.getBusinessName() != null && !searchDto.getBusinessName().trim().isBlank()) {
			sql.append("AND business_info_report.businessname LIKE CONCAT('%', :businessname, '%') "
					+ System.lineSeparator());
			params.put("businessname", searchDto.getBusinessName());
		}

		if (searchDto.getType() != null && searchDto.getType() != 0) {
			sql.append("AND business_info_report.type = :type" + System.lineSeparator());
			params.put("type", searchDto.getType());
		}
		if (searchDto.getCategory() != null && searchDto.getCategory() != 0) {
			sql.append("AND business_info_report.Category = :category" + System.lineSeparator());
			params.put("category", searchDto.getCategory());
		}
		if (searchDto.getVertical() != null && searchDto.getVertical() != 0) {
			sql.append("AND business_info_report.Industry = :industry" + System.lineSeparator());
			params.put("industry", searchDto.getVertical());
		}
		if (searchDto.getIndustry() != null && searchDto.getIndustry() != 0) {
			sql.append("AND business_info_report.subIndustry = :subIndustry" + System.lineSeparator());
			params.put("subIndustry", searchDto.getIndustry());
		}
		if (searchDto.getStartDate() != null) {
			sql.append("AND business_info_report.StartDate= :startDate" + System.lineSeparator());
			params.put("startDate", searchDto.getStartDate());
		}
		if (searchDto.getEndDate() != null) {
			sql.append("AND business_info_report.EndDate= :endDate" + System.lineSeparator());
			params.put("endDate", searchDto.getEndDate());
		}
		if (searchDto.getCustRegistrationNumber() != null && searchDto.getCustRegistrationNumber() != 0) {
			sql.append("AND business_info_report.custRegistrationNumber = :custRegistrationNumber"
					+ System.lineSeparator());
			params.put("custRegistrationNumber", searchDto.getCustRegistrationNumber());
		}
		if (searchDto.getBusinessUnit() != null && searchDto.getBusinessUnit() != 0) {
			sql.append("AND business_info_report.businessUnitId = :businessUnitId" + System.lineSeparator());
			params.put("businessUnitId", searchDto.getBusinessUnit());
		}
		if (searchDto.getOrganization() != null && searchDto.getOrganization() != 0) {
			sql.append("AND business_info_report.organizationId = :organization" + System.lineSeparator());
			params.put("organization", searchDto.getOrganization());
		}
		if (searchDto.getLocation() != null && searchDto.getLocation() != 0) {
			sql.append("AND business_info_report.locationId = :location" + System.lineSeparator());
			params.put("location", searchDto.getLocation());
		}
		if (searchDto.getCostCenter() != null && searchDto.getCostCenter() != 0) {
			sql.append("AND business_info_report.costCenterId = :costCenter" + System.lineSeparator());
			params.put("costCenter", searchDto.getCostCenter());
		}

		// sql.append("ORDER BY ci.CUSTOMERID");
		Query query = entityManager.createNativeQuery(sql.toString(), "business_information_search");

		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());

		}

		@SuppressWarnings("unchecked")
		List<CustomerSearchInfoResponse> resultList = query.getResultList();

		return resultList;
	}

}
