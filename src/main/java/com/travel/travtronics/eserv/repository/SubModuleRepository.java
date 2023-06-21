package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.eserv.model.SubModule;

public interface SubModuleRepository extends JpaRepository<SubModule, Long>{

	List<SubModule> findAllByOrganizationId(Long organizationId);

	Page<SubModule> findByOrganizationId(Long organizationId, Pageable pageable);

}
