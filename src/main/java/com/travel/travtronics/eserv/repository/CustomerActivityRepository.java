package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.eserv.model.CustomerActivity;

public interface CustomerActivityRepository extends JpaRepository<CustomerActivity, Long> {

	@Query("select activity from CustomerActivity activity where activity.activityId= :activityId")
	Optional<CustomerActivity> getActivityById(@Param("activityId") Long activityId);

	@Query(value = "SELECT * FROM customer_activity", nativeQuery = true)
	List<CustomerActivity> getAllActivities();

	@Query(value = "SELECT\r\n" + "ca.ID AS id,\r\n" + "ca.ActivityId AS activityId,\r\n"
			+ "ma.AcitivityName AS acitivityName,\r\n" + "ca.SubActivityId AS subActivityId,\r\n"
			+ "GROUP_CONCAT(DISTINCT(msa.SubAcitivityName) ORDER BY msa.Id) AS subAcitivityName, \r\n"
			+ "ca.CustomerId AS customerId, \r\n" + "ca.OrganizationId AS organizationId,\r\n"
			+ "mo.Name AS organizationName,\r\n" + "ci.BusinessName AS businessName, \r\n"
			+ "ca.SegementId AS segementId, \r\n" + "ms.SegmentName AS segmentName,\r\n"
			+ "ca.StartDate AS startDate, \r\n" + "ca.EndDate AS endDate, \r\n" + "ca.Status AS status, \r\n"
			+ "ca.CreatedBy AS createdBy, \r\n" + "ca.CreatedDate AS createdDate,\r\n"
			+ "ca.UpdatedBy AS updatedBy, \r\n" + "ca.UpdatedDate AS updatedDate\r\n" + "FROM customer_activity ca\r\n"
			+ "LEFT JOIN master_activity AS ma ON ma.Id = ca.ActivityId\r\n"
			+ "LEFT JOIN master_organization AS mo ON mo.OrganizationId = ca.OrganizationId\r\n"
			+ "LEFT JOIN master_sub_activity AS msa ON FIND_IN_SET(msa.Id, ca.SubActivityId) > 0\r\n"
			+ "LEFT JOIN customer_info AS ci ON ci.CUSTOMERID = ca.CustomerId\r\n"
			+ "LEFT JOIN master_segments AS ms ON ms.Id = ca.SegementId\r\n"
			+ "WHERE ca.customerId= ?1 AND ca.OrganizationId = ?2\r\n" + "GROUP BY ca.ID", nativeQuery = true)
	List<Map<String, String>> findByCustomerIdList(Integer customerId, Long organizationId);
//	List<CustomerActivity> getActivitiesByCustomerId(@Param("customerId") Integer customerId);

	Page<CustomerActivity> findByOrganizationIdAndCustomerId(Long organizationId, Long customerId, Pageable pageable);

	@Query(value = "SELECT c.SubAcitivityName AS SubAcitivityName FROM master_sub_activity  c WHERE c.Id=?1", nativeQuery = true)
	Optional<String> getSubActivityName(List<Integer> subActivityId);

	@Query(value = "SELECT c.BusinessName AS customerName FROM customer_info  c WHERE c.CUSTOMERID=?1", nativeQuery = true)
	Optional<String> getCustomerName(Long id);

	@Query(value = "SELECT c.SegmentName AS secondPartyIdName FROM master_segments  c WHERE c.Id=?1", nativeQuery = true)
	Optional<String> getSegmentName(Long segementId);

	@Query(value = " SELECT c.AcitivityName AS AcitivityName FROM master_activity  c WHERE c.Id=?1", nativeQuery = true)
	Optional<String> getActivityName(Long id);

	@Query(value = "SELECT c.SubAcitivityName AS SubAcitivityName FROM master_sub_activity  c WHERE c.Id IN(:id)", nativeQuery = true)
	List<String> getSubActivityNameInfo(@Param("id") List<Integer> id);

//	@Query(value = " SELECT c.AcitivityName AS AcitivityName FROM master_activity  c WHERE c.Id=?1",nativeQuery = true)
//	Optional<String> getSubActivityName(Long activityId);
}
