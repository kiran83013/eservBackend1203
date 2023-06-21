package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.ShiftsModel;

public interface ShiftsRepository extends JpaRepository<ShiftsModel, Long> {

	@Query(value = "SELECT * FROM shifts_manager WHERE shift_id = ?1 "
			+ "AND (end_date IS NULL OR DATE_FORMAT(end_date ,'%Y-%m') > DATE_FORMAT(?2,'%Y-%m')) "
			+ "AND (start_date IS NULL OR DATE_FORMAT(start_date ,'%Y-%m') <= DATE_FORMAT(?2,'%Y-%m'))", nativeQuery = true)
	Optional<ShiftsModel> getShiftInfoByIdAndDate(Long shiftId, String inputDate);
	
	@Query("select sm from ShiftsModel sm where sm.id=?1")
	Optional<ShiftsModel> findByShiftId(Long shiftId);

	List<ShiftsModel> findByOrgId(Long orgId);

}
