package com.travel.travtronics.eserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.travel.travtronics.eserv.model.CustomerSearchEntity;

public interface CustomerSearchEntityRepository
		extends JpaRepository<CustomerSearchEntity, Long>, JpaSpecificationExecutor<CustomerSearchEntity> {

}
