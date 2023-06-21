package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.eserv.model.BreaksHeaderModel;


public interface BreaksHeaderRepository extends JpaRepository<BreaksHeaderModel, Long> {

	List<BreaksHeaderModel> findByOrgId(Long orgId);

}
