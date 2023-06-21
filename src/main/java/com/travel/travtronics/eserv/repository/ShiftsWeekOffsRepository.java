package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.eserv.model.ShiftsWeekOffsModel;


public interface ShiftsWeekOffsRepository extends JpaRepository<ShiftsWeekOffsModel, Long> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM shifts_week_offs WHERE shift_id = :shiftId", nativeQuery = true)
	void deleteWeekOffsByShiftId(@Param("shiftId") Long shiftId);

	@Query(value = "SELECT * FROM shifts_week_offs WHERE shift_id = ?1 AND record_status = 'Active'", nativeQuery = true)
	List<ShiftsWeekOffsModel> findByShiftId(Long shiftId);

	@Query(value = "SELECT * FROM shifts_week_offs WHERE shift_id = ?1 AND week_off_day = ?2 AND record_status = 'Active'", nativeQuery = true)
	Optional<ShiftsWeekOffsModel> isThisDayWeekOffByShiftIdDayNo(Long shiftId, int dayOfWeekNo);

}
