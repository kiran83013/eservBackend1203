package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.travel.travtronics.eserv.model.PriceTypeItems;

public interface PriceTypeItemsRepository extends CrudRepository<PriceTypeItems, Long> {

	List<PriceTypeItems> findAllByStatusTrue();

}
