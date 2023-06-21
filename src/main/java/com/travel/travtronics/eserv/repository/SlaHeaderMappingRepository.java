package com.travel.travtronics.eserv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.travel.travtronics.eserv.model.SlaHeaderMappingModel;

public interface SlaHeaderMappingRepository extends JpaRepository<SlaHeaderMappingModel, Long>, JpaSpecificationExecutor<SlaHeaderMappingModel> {
	
	Page<SlaHeaderMappingModel> findByOrgId(Integer orgId, Pageable pageable);

	
}
