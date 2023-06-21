package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.travel.travtronics.eserv.model.SanctionViewSetUpModel;

public interface SanctionViewSetUpRepository extends CrudRepository<SanctionViewSetUpModel, Long> {
	
	
	List<SanctionViewSetUpModel> findAll();

}
