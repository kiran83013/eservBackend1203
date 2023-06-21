package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.UserShiftAssignmentModel;
import com.travel.travtronics.util.QueryConst;

public interface UserShiftAssignmentRepository extends JpaRepository<UserShiftAssignmentModel, Long>{

	Boolean existsByUserIdAndShiftId(Long userId, Long shiftId);

	@Query(value = QueryConst.GET_SHIFT_USER_MAPPING_LIST, nativeQuery = true)
	List<Map<String, Object>> getShitUserMappedList(Long orgId);

	@Query(value = "SELECT * FROM user_shift_assignments WHERE user_id = ?1 AND end_date >= NOW() AND STATUS = 'Active'", nativeQuery = true)
	List<UserShiftAssignmentModel> getUserShiftAssignmentsByUserId(Long userId);

	


}
