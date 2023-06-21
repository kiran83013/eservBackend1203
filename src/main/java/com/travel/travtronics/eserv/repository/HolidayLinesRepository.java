package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.HolidayLinesModel;
import com.travel.travtronics.util.QueryConst;


public interface HolidayLinesRepository extends JpaRepository<HolidayLinesModel, Long> {

	
	List<HolidayLinesModel> findAllByHeaderIdAndIsDeletedIsNull(Long headerId);

	@Query(value = QueryConst.HOLIDAY_CAL_DATE_CHECK_JOIN_QRY, nativeQuery = true)
	Optional<Map<String, Object>> isThisDayHolidayByHolidayCalIdAndDate(Integer holidayCalId, String currentDateStr);
}
