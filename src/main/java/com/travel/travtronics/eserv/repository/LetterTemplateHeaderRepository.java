package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.eserv.model.LetterTemplateHeader;

public interface LetterTemplateHeaderRepository extends JpaRepository<LetterTemplateHeader, Long>{

	List<LetterTemplateHeader> findAllByOrganizationId(Long organizationId);

}
