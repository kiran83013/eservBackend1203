package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.eserv.model.ShiftsExculedMonthsModel;


public interface ShiftsExculedMonthsRepository extends JpaRepository<ShiftsExculedMonthsModel, Long> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM shifts_exculed_months WHERE shift_id = :shiftId", nativeQuery = true)
	void deleteExMonthsByShiftId(@Param("shiftId") Long shiftId);

	@Query(value = "SELECT * FROM shifts_exculed_months WHERE shift_id = ?1 AND record_status = 'Active'", nativeQuery = true)
	List<ShiftsExculedMonthsModel> findByShiftId(Long shiftId);

	@Query(value = "SELECT * FROM shifts_exculed_months WHERE shift_id = ?1 AND month_no = ?2 AND record_status = 'Active'", nativeQuery = true)
	Optional<ShiftsExculedMonthsModel> checkExcludedMonthByShiftIdMonthNo(Long shiftId, Integer monthNo);

}
