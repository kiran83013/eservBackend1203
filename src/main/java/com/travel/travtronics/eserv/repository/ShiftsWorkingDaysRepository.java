package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.eserv.model.ShiftsWorkingDaysModel;


public interface ShiftsWorkingDaysRepository extends JpaRepository<ShiftsWorkingDaysModel, Long> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM shifts_working_days WHERE shift_id = :shiftId", nativeQuery = true)
	void deleteWorkingDaysByShiftId(@Param("shiftId") Long shiftId);

	@Query(value = "SELECT * FROM shifts_working_days WHERE shift_id = ?1 AND record_status = 'Active'", nativeQuery = true)
	List<ShiftsWorkingDaysModel> getAllWorkingDaysByShiftId(Long shiftId);

	@Query(value = "SELECT * FROM shifts_working_days WHERE shift_id = ?1 AND month_no = ?2 "
			+ "AND day_no = ?3 AND record_status = 'Active'", nativeQuery = true)
	Optional<ShiftsWorkingDaysModel> isThisDayWorkingDayByShiftIdMonthNoDayNo(Long shiftId, Integer monthNo, int d);

}
