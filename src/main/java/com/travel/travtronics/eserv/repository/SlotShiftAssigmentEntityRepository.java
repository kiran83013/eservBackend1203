package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.eserv.model.SlotShiftAssigmentEntity;
import com.travel.travtronics.util.QueryConst;

public interface SlotShiftAssigmentEntityRepository extends CrudRepository<SlotShiftAssigmentEntity, Long> {

	@Query(value = QueryConst.GET_ALL_SLOT_SHIFT_ASSIGNMENTS, nativeQuery = true)
	List<SlotShiftAssigmentEntity> findByOrgId(@Param("orgId") Long orgId);

}
