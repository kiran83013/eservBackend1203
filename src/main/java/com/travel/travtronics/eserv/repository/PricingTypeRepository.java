package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.travel.travtronics.eserv.model.PriceType;

public interface PricingTypeRepository extends CrudRepository<PriceType, Long> {

	List<PriceType> findAllByStatusTrue();

}
