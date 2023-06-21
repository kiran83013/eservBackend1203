package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.enums.Status;
import com.travel.travtronics.eserv.model.EmpAddress;

public interface EmpAddressRepository extends JpaRepository<EmpAddress, Long>{

	Optional<EmpAddress> findByAddressId(Long addressId);

	Page<EmpAddress> findByOrganizationId(Long organizationId, Pageable pageable);

	List<EmpAddress> findAllByRefIdAndOrganizationIdAndStatus(Long refId, Long organizationId, Status active);

}
