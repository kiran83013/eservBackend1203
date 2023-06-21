package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.eserv.model.BpfMailTemplate;

public interface BpfMailTemplateRepository extends JpaRepository<BpfMailTemplate, Long>{

	List<BpfMailTemplate> findAllByOrganizationId(Long organizationId);
	
	Page<BpfMailTemplate>findByOrganizationId(Long organizationId, Pageable pageable);

}
