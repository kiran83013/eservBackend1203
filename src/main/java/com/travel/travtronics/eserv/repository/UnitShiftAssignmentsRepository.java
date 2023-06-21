package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.eserv.model.UnitShiftAssignments;
import com.travel.travtronics.util.QueryConst;

@Repository
public interface UnitShiftAssignmentsRepository
		extends JpaRepository<UnitShiftAssignments, Long>, UnitCustomSearchRepository {

	Boolean existsByUnitIdAndShiftId(Long unitId, Long shiftId);

	@Query(value = QueryConst.GET_SHIFT_UNIT_MAPPING_LIST, nativeQuery = true)
	List<Map<String, Object>> getShitUserMappedList(Long orgId);

	@Query(value = "SELECT mo.name AS organizationName FROM  master_organization mo WHERE mo.OrganizationId=?1", nativeQuery = true)
	Optional<String> getOrgName(Long orgId);

	@Query(value = "SELECT sm.shift_name AS ShiftName FROM  shifts_manager sm WHERE sm.shift_id=?1", nativeQuery = true)
	Optional<String> getShiftName(Long shiftId);

	@Query(value = "SELECT slth.name AS slotTemplateName FROM  slots_template_header slth WHERE slth.id=?1", nativeQuery = true)
	Optional<String> getSlotTemplateName(Long slotTemplateId);

	@Query(value = "SELECT ui.Unit_Name AS unitName FROM  unit_information ui WHERE ui.ID=?1", nativeQuery = true)
	Optional<String> getUnitName(Long unitId);
	
	@Query(value = "SELECT ui.Unit_Name AS unitName FROM  unit_shift_slot_assignments usa\r\n" + 
			"INNER JOIN unit_information ui ON usa.unit_id = ui.ID  WHERE ui.Unit_Name LIKE %?%", nativeQuery = true)
	List<Map<String, String>> FindByUnitNames(String unitName);


	
}
