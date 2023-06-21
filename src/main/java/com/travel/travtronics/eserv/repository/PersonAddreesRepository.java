package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.enums.Status;
import com.travel.travtronics.eserv.model.PersonAddreesModel;

public interface PersonAddreesRepository extends JpaRepository<PersonAddreesModel, Long> {

	List<PersonAddreesModel> findAllByRefIdAndOrganizationIdAndStatus(Long refId, Long orgId, Status active);

	Page<PersonAddreesModel> findByOrganizationId(Long orgId, Pageable page);
}
