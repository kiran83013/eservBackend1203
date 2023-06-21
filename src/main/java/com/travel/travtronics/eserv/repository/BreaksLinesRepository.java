package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.eserv.model.BreaksLinesModel;


public interface BreaksLinesRepository extends JpaRepository<BreaksLinesModel, Long> {

	List<BreaksLinesModel> findAllByHeaderIdAndIsDeletedIsNull(Long headerId);

}
