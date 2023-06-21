package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.UserShiftsSlotAssignmentModel;
import com.travel.travtronics.util.QueryConst;

public interface UserShiftsSlotAssignmentRepository extends JpaRepository<UserShiftsSlotAssignmentModel, Long> {

//	@Query(value = QueryConst.GET_ALL_SLOT_SHIFT_ASSIGNMENTS, nativeQuery = true)
//	List<Map<String, String>> getALL();

	Boolean existsByUserIdAndShiftIdAndSlotTemplateId(Long unitId, Long shiftId, Long slotTemplateId);

	@Query(value = "SELECT * FROM user_shift_slot_assignments WHERE user_id = ?1 AND shift_id = ?2 "
			+ "AND (end_date IS NULL OR DATE_FORMAT(end_date ,'%Y-%m-%d') > DATE_FORMAT(?3,'%Y-%m-%d')) "
			+ "AND (start_date IS NULL OR DATE_FORMAT(start_date ,'%Y-%m-%d') <= DATE_FORMAT(?3,'%Y-%m-%d')) "
			+ "AND status = 'Active'", nativeQuery = true)
	List<UserShiftsSlotAssignmentModel> getUserShiftsByUserIdShiftId(Long userId, Long shiftId, String inputDate);
	

}
