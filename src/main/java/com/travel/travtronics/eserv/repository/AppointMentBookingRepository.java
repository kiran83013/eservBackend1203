package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.eserv.model.AppointMentBookingModel;
import com.travel.travtronics.util.QueryConst;

@Repository
public interface AppointMentBookingRepository extends JpaRepository<AppointMentBookingModel, Long>,UserCustomSearchRepository {

	@Query(value = QueryConst.GET_BOOKED_APPOINTMENT_LIST, nativeQuery = true)
	List<Map<String, Object>> getBookedAppointMentList(Long orgId);

	@Query(QueryConst.BOOKED_APPOINTMENT_VALIDATION)
	Boolean generateBookingValidation(Long shiftId, Long slotId, Long slotLineId, Long userId);

}
