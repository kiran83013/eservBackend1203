package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.UnitShiftsSlotAssignmentModel;
import com.travel.travtronics.util.QueryConst;

public interface UnitShiftsSlotAssignmentRepository extends JpaRepository<UnitShiftsSlotAssignmentModel, Long>{

	Boolean existsByUnitIdAndShiftIdAndSlotTemplateId(Long unitId, Long shiftId, Long slotTemplateId);
    
	@Query(value = QueryConst.GET_ALL_UNIT_SLOT_SHIFT_ASSIGNMENTS, nativeQuery = true)
	List<Map<String, String>> getByOrgId(Long orgId);

	

}
