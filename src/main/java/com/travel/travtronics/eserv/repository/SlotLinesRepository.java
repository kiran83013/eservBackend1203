package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.SlotLinesModel;


public interface SlotLinesRepository extends JpaRepository<SlotLinesModel, Long> {

	List<SlotLinesModel> findAllByHeaderIdAndIsDeletedIsNull(Long headerId);

	@Query(name = "SELECT * FROM slots_template_lines WHERE header_id = ?1 AND is_deleted != 1", nativeQuery = true)
	List<SlotLinesModel> getSlotsListInfoByHeaderId(Long id);
}
