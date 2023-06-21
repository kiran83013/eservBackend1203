package com.travel.travtronics.eserv.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.travel.travtronics.request.SearchUserDto;
import com.travel.travtronics.response.UserSearchResponse;

public class UserCustomSearchRepositoryImpl implements UserCustomSearchRepository {
	
	@PersistenceContext
	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<UserSearchResponse> searchUserShiftInfo(SearchUserDto search) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT  travuser.user_id AS userId,travuser.costcentervalue AS costCenter,\r\n" + 
				"cc.Name AS costCenterName,travuser.createddate AS userCreatedDate,\r\n" + 
				"travuser.dept_id AS department ,dept.Name AS departmentName,travuser.queue_email_address AS email,\r\n" + 
				"travuser.phonenumber AS phoneNumber,travuser.firstname AS firstName,\r\n" + 
				"travuser.lastname AS lastName,travuser.fullname AS userName,travuser.location_id AS location,\r\n" + 
				"loc.Name AS locationName, travuser.is_supervisor AS supervisor,\r\n" + 
				"supervisoruser.user_name AS supervisorName,shift.start_time AS shiftFrom,\r\n" + 
				"shift.end_time AS shiftTo,shift.shift_id AS shiftId,shift.shift_name AS shiftName,\r\n" + 
				"shift.timie_zone AS timeZone,tz.TimeZone AS timeZoneName,uss.slot_template_id AS slotTemplateId,\r\n" + 
				"sth.name AS slotTemplateName,\r\n" + 
				"usershift.id AS userShiftAssignmentId,\r\n" + 
				"uss.id AS userShiftSlotAssignmentId  FROM  e_services_be_user.a3m_account_details travuser\r\n" + 
				"LEFT JOIN e_services.cost_center cc ON cc.CostcenterId=travuser.costcentervalue\r\n" + 
				"LEFT JOIN e_services.master_departments dept ON dept.DepartmentId=travuser.dept_id\r\n" + 
				"LEFT JOIN e_services.location loc ON loc.LocationId=travuser.location_id\r\n" + 
				"LEFT JOIN e_services.user_info supervisoruser ON supervisoruser.user_id=travuser.is_supervisor\r\n" + 
				"LEFT JOIN e_services.user_shift_assignments usershift ON usershift.user_id=travuser.user_id\r\n" + 
				"INNER JOIN e_services.shifts_manager shift ON shift.shift_id=usershift.shift_id\r\n" + 
				"LEFT JOIN e_services.user_shift_slot_assignments uss ON uss.user_id=usershift.user_id AND uss.shift_id=usershift.shift_id\r\n" + 
				"LEFT JOIN e_services.slots_template_header sth ON sth.id=uss.slot_template_id\r\n" + 
				"INNER JOIN e_services.master_timezone tz ON tz.ID=shift.timie_zone WHERE 1=1\r\n" + 
				"\r\n" + 
				""+System.lineSeparator());
		
		if (search.getLocationId() != null && !search.getLocationId().trim().isBlank()) {
			sql.append("AND travuser.location_id= :locationId" + System.lineSeparator());
			params.put("locationId", search.getLocationId());
		}

		if (search.getUserId() != null && search.getUserId() != 0) {
			sql.append("AND travuser.user_id= :userId" + System.lineSeparator());
			params.put("userId", search.getUserId());
		}
		if (search.getCostCenterId() != null && !search.getCostCenterId().trim().isBlank()) {
			sql.append("AND travuser.costcentervalue= :costCenterId" + System.lineSeparator());
			params.put("costCenterId", search.getCostCenterId());
		}

		if (search.getDepartmentId() != null && !search.getDepartmentId().trim().isBlank()) {

			sql.append("AND travuser.dept_id= :departmentId" + System.lineSeparator());
			params.put("departmentId", search.getDepartmentId());
		}

		if (search.getSupervisorId() != null && !search.getSupervisorId().isBlank()) {
			sql.append("AND travuser.is_supervisor= :supervisorId" + System.lineSeparator());
			params.put("supervisorId", search.getSupervisorId());
		}
		if (search.getShiftFrom() != null && !search.getShiftFrom().trim().isBlank()) {
			sql.append("AND shift.start_time= :shiftFrom" + System.lineSeparator());
			params.put("shiftFrom", search.getShiftFrom());
		}

		if (search.getShiftTo() != null && !search.getShiftTo().isBlank()) {
			sql.append("AND shift.end_time= :shiftTo" + System.lineSeparator());
			params.put("shiftTo", search.getShiftTo());
		}

		if (search.getShiftId() != null && search.getShiftId() != 0) {
			sql.append("AND usershift.shift_id= :shiftId" + System.lineSeparator());
			params.put("shiftId", search.getShiftId());
		}
		
		if (search.getTimeZone() != null && !search.getTimeZone().isBlank()) {
			sql.append("AND shift.timie_zone= :timeZone" + System.lineSeparator());
			params.put("timeZone", search.getTimeZone());
		}
		
		sql.append("ORDER BY travuser.user_id,shift.shift_id,uss.slot_template_id");
		
		Query query = entityManager.createNativeQuery(sql.toString(),"user_shift_slot_search");

		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());

		}
		@SuppressWarnings("unchecked")
		List<UserSearchResponse> resultList = query.getResultList();

		return resultList;
	}

}
