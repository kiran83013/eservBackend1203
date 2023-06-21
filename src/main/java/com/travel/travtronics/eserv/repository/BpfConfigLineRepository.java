package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.enums.StatusActive;
import com.travel.travtronics.eserv.model.BpfConfigLine;


public interface BpfConfigLineRepository extends JpaRepository<BpfConfigLine, Long> {

	List<BpfConfigLine> findAllByHeaderIDAndDeleteFlagIsNullAndStatus(Long id, StatusActive active);

}
