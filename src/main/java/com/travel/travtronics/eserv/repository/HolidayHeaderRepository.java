package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.eserv.model.HolidayHeaderModel;


public interface HolidayHeaderRepository extends JpaRepository<HolidayHeaderModel, Long> {

	List<HolidayHeaderModel> findByOrgId(Long orgId);

}
