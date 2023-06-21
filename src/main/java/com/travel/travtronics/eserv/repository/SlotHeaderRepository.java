package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.SlotHeaderModel;
import com.travel.travtronics.util.QueryConst;

public interface SlotHeaderRepository extends JpaRepository<SlotHeaderModel, Long> {

	@Query(value = QueryConst.GET_SLOT_INFO_BY_SHIFT_ID, nativeQuery = true)
	List<SlotHeaderModel> getSlotInfoByShiftId(Long shiftId);

	@Query(value = "SELECT * FROM slots_template_header WHERE id = ?1 AND status = 'Active'", nativeQuery = true)
	Optional<SlotHeaderModel> getSlotHeaderInfoById(Long slotTemplateId);

	List<SlotHeaderModel> findByOrgId(Long orgId);

}
